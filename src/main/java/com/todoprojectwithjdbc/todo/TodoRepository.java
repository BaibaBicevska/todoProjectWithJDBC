package com.todoprojectwithjdbc.todo;


import com.todoprojectwithjdbc.todo.Todo;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class TodoRepository {

public TodoRepository(){}



    private Connection getConnection(){
        String username = "root";
        String password = "root";
        String database = "todos";
        String url = "jdbc:mysql://127.0.0.1:3306/sys";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }



    public void createTodo(Todo todo){
        String query = "INSERT INTO todos(description, isComplete) VALUES(?,?)";

        try{
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setString(1, todo.getDescription());
            statement.setBoolean(2, todo.isComplete());
            statement.executeUpdate();

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    //conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "root");
    public ArrayList<Todo> findAllTodos() {

        ArrayList<Todo> todoList = new ArrayList<>();
        String query = "SELECT * FROM todos";
        try {
            PreparedStatement stat = getConnection().prepareStatement(query);
            ResultSet result = stat.executeQuery();
            while(result.next()){
                todoList.add(new Todo(
                        result.getInt("id"),
                        result.getString("description"),
                        result.getBoolean("isComplete"),
                        result.getTimestamp("createdAt"),
                        result.getTimestamp("updatedAt")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    public void deleteTodoItem(Integer todoId) {
        String query = "DELETE FROM todos WHERE id= ?";

        try{
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, todoId);
            statement.executeUpdate();

               }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTodoItem(int todoId) {
        String query = "UPDATE todos SET isComplete = true WHERE id= ?";

        try{
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, todoId);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void updateTodoStatus(int todoId) {
        String query = "UPDATE todos SET isComplete = true WHERE id = ?";

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(query);

            statement.setInt(1, todoId);

            statement.executeUpdate();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
