CREATE DATABASE IF NOT EXISTS todo_db;
USE todo_db;

CREATE TABLE IF NOT EXISTS tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

DELIMITER //
CREATE PROCEDURE add_task(IN p_task_name VARCHAR(255), IN p_status VARCHAR(50))
BEGIN
    INSERT INTO tasks(task_name, status) VALUES (p_task_name, p_status);
END //
CREATE PROCEDURE list_tasks()
BEGIN
    SELECT id, task_name, status FROM tasks;
END //
CREATE PROCEDURE update_task_status(IN p_id INT, IN p_status VARCHAR(50))
BEGIN
    UPDATE tasks SET status = p_status WHERE id = p_id;
END //
CREATE PROCEDURE delete_task(IN p_id INT)
BEGIN
    DELETE FROM tasks WHERE id = p_id;
END //
CREATE PROCEDURE search_task_by_name(IN p_task_name VARCHAR(255))
BEGIN
    SELECT id, task_name, status FROM tasks WHERE task_name LIKE CONCAT('%', p_task_name, '%');
END //
CREATE PROCEDURE task_statistics()
BEGIN
    SELECT status, COUNT(*) AS total FROM tasks GROUP BY status;
END //
DELIMITER ;
