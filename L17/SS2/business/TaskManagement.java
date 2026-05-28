package business;

import model.Task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {
    public void addTask(String taskName, String status) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL add_task(?, ?)}")) {
            statement.setString(1, taskName);
            statement.setString(2, status);
            statement.executeUpdate();
        }
    }

    public List<Task> listTasks() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL list_tasks()}");
             ResultSet resultSet = statement.executeQuery()) {
            return readTasks(resultSet);
        }
    }

    public void updateTaskStatus(int taskId, String status) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL update_task_status(?, ?)}")) {
            statement.setInt(1, taskId);
            statement.setString(2, status);
            statement.executeUpdate();
        }
    }

    public void deleteTask(int taskId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL delete_task(?)}")) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        }
    }

    public List<Task> searchTaskByName(String taskName) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL search_task_by_name(?)}")) {
            statement.setString(1, taskName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return readTasks(resultSet);
            }
        }
    }

    public void taskStatistics() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL task_statistics()}");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("status") + ": " + resultSet.getInt("total"));
            }
        }
    }

    private List<Task> readTasks(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            tasks.add(new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("task_name"),
                    resultSet.getString("status")
            ));
        }
        return tasks;
    }
}
