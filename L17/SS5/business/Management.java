package business;

import model.Assignment;
import model.Employee;
import model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Management {
    public boolean addEmployee(Employee employee) throws SQLException {
        if (existsByName("employee", employee.getName())) return false;
        String sql = "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setBigDecimal(3, employee.getSalary());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean addProject(Project project) throws SQLException {
        if (existsByName("project", project.getName())) return false;
        String sql = "INSERT INTO project(name, budget) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getName());
            statement.setBigDecimal(2, project.getBudget());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean assignEmployeeToProject(Assignment assignment) throws SQLException {
        if (!existsById("employee", assignment.getEmployeeId()) || !existsById("project", assignment.getProjectId())) {
            return false;
        }
        String sql = "INSERT INTO assignment(employee_id, project_id, role) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, assignment.getEmployeeId());
            statement.setInt(2, assignment.getProjectId());
            statement.setString(3, assignment.getRole());
            return statement.executeUpdate() > 0;
        }
    }

    public List<String> listEmployeesAndProjects() throws SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT e.name AS employee_name, p.name AS project_name, a.role " +
                "FROM assignment a JOIN employee e ON a.employee_id = e.id JOIN project p ON a.project_id = p.id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getString("employee_name") + " - " + resultSet.getString("project_name") +
                        " - " + resultSet.getString("role"));
            }
        }
        return result;
    }

    public boolean updateEmployeeSalary(int employeeId, java.math.BigDecimal newSalary) throws SQLException {
        if (!existsById("employee", employeeId)) return false;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE employee SET salary = ? WHERE id = ?")) {
            statement.setBigDecimal(1, newSalary);
            statement.setInt(2, employeeId);
            return statement.executeUpdate() > 0;
        }
    }

    private boolean existsByName(String table, String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM " + table + " WHERE name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private boolean existsById(String table, int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM " + table + " WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
