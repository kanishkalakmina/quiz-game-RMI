 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;



import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lakmina
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface  {
/*
    server class inherit all the methods from the inetrface
    */
    public RemoteServer() throws RemoteException {
        super();
    }
    /*
    Server Side of the quiz application
    */
    public static void main(String[] args){
          
        try {
            /*
            Start Server
            */
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            RemoteServer obj = new RemoteServer();
            
            reg.rebind("rmi://localhost/MyService", obj);
            System.out.println("Server is Running...");
            
             
        } catch (RemoteException ex) {
            
            System.out.println(ex.getMessage());
         
        }
    }

    @Override
    
    public String LoginForm(String email, String password) throws RemoteException {
     
    /*
        check email and password is matched with DB records
        
    */
        
        String response = "";
        
        try {
            ResultSet rs = DB_Connection.search("SELECT * FROM user WHERE email='"+email+"'");
            
            if(!rs.next()){
                response = "Incorrect email..";
            }else{
                if(rs.getString("password").equals(password)){
                    response = "Login Successful.";
                    
                }else{
                    response = "Incorrect password..";
                }
            }
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return response;
        }
        
    }

    @Override
    public String RegisterForm(String username, String email, String password, String confirm_password) throws RemoteException {
     
    /*
        validate password
        insert the user details to the database
    */
        String response_reg = " "; 
        
        if(password.equals(confirm_password)){
            
            try{
                
                DB_Connection.iud("INSERT INTO user(username,email,password) values ('"+username+"','"+email+"','"+password+"')");
                
            }catch( Exception ex){   
            ex.printStackTrace();
            }
            response_reg = "Register Successful.";
        }else{
            response_reg = "Check the password.";
        }
        return response_reg;
        
    }

    @Override
    public String AdminForm(String question, String option_1, String option_2, String option_3, String option_4, String answer) throws RemoteException {
    /*
        insert the questions and answers to the database
    */    
        String response_admin = "";
        
        try{
            
            DB_Connection.iud("INSERT INTO questions(question,option1,option2,option3,option4,answer) values ('"+question+"','"+option_1+"','"+option_2+"','"+option_3+"','"+option_4+"','"+answer+"')");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        response_admin = "Values Added.";
       return  response_admin; 
    }

    @Override
    public String AdminFormupdate(String question, String option_1, String option_2, String option_3, String option_4, String answer) throws RemoteException {
         String response_up_admin = "";
        
        
        return null;
    }

   

    @Override
    public String SearchValuesForm(String id) throws RemoteException {
       
        String respons_admin = "";
        
       // ArrayList sdetails = new ArrayList();
       // ArrayList<sdetails> searchclass = ListProduct(id);
        
        try{
            
             ResultSet rs = DB_Connection.search("SELECT * FROM questions WHERE id='"+id+"'");
          
             if(!rs.next()){
                 
                 respons_admin = "Invalid ID";
             }
             else{
                 if(rs.getString("id").equals(id)){
                     
                     respons_admin = "valid ID";
             
                    
                        
                 }
             }
             
             
             /*
                  while(rs.next()){
                      
                 sdetails.add("question"+rs.getString("questions"));
                 sdetails.add("Option1"+rs.getString("option1"));
                 sdetails.add("Option2"+rs.getString("option2"));
                 sdetails.add("Option3"+rs.getString("option3"));
                 sdetails.add("Option4"+rs.getString("option4"));
                 sdetails.add("answer"+rs.getString("answer"));  
             }
*/
             
          
            
            
        }catch(Exception ex){}
      
       return respons_admin;
    
    }

    @Override
    public ArrayList<Questions> getQuestions() throws RemoteException {
        
        try{
            
        ArrayList<Questions> list = new   ArrayList<Questions>();
        ArrayList<String>  Question = new ArrayList<String>();
        ArrayList<String>  Option1 = new ArrayList<String>();
        ArrayList<String>  Option2 = new ArrayList<String>();
        ArrayList<String>  Option3 = new ArrayList<String>();
        ArrayList<String>  Option4 = new ArrayList<String>();
        
         ResultSet rs = DB_Connection.search("SELECT * FROM questions");
        
         Questions questions = new Questions();
         while(rs.next()){
             
             String id = rs.getString("id");
        
             Question.add(rs.getString("question"));
             Option1.add(rs.getString("option1"));
             Option2.add(rs.getString("option2"));
             Option3.add(rs.getString("option3"));
             Option4.add(rs.getString("option4"));
             
             //Setting the values
             
            questions.setQuestions(Question);
            questions.setOption1(Option1);
            questions.setOption2(Option2);
            questions.setOption3(Option3);
            questions.setOption4(Option4);

            return list;
            
            //https://github.com/Kasun-thilina/DreamMobile_Server
            //https://github.com/Kasun-thilina/DreamMobile_Client
         }
         
         
         
        
        }catch(Exception ex){}
        
       
        return null;
    }

    @Override
    public String UpdateQues(Question updateques) throws RemoteException {
        PreparedStatement statement = null;
        
        String sql = "update questions set question = ? "
                +", option1 = ?, option2 = ? , option3 = ?,option4 = ?,answer = ?"
                +"where id = ?";
        
        try{
            
            statement = DB_Connection.getConnection().prepareStatement(sql);
            statement.setString(1, updateques.getQuestion());
            statement.setString(2, updateques.getOption1());
            statement.setString(3, updateques.getOption2());
            statement.setString(4, updateques.getOption3());
            statement.setString(5, updateques.getOption4());
            statement.setString(5, updateques.getAnswer());
            
            statement.executeUpdate();
        }catch(Exception ex){
        ex.printStackTrace();
        }
        finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
        return null;
        
    }

    @Override
    public Question getQuestionById(String id) throws RemoteException {
        PreparedStatement statement = null;
        
        String sql = "select * from questions where id = ?";
        
        try{
            statement = DB_Connection.getConnection().prepareStatement(sql);
            statement.setString(1, id);
            
            ResultSet result = statement.executeQuery();
            
            Question updateques = null;
            if(result.next()){
                updateques.setId(result.getString("id"));
                updateques.setQuestion(result.getString("question"));
                updateques.setOption1(result.getString("option1"));
                updateques.setOption1(result.getString("option2"));
                updateques.setOption1(result.getString("option3"));
                updateques.setOption1(result.getString("option4"));
                updateques.setAnswer(result.getString("answer"));
            }
            result.close();
            return updateques;
        }catch(Exception ex){
        ex.printStackTrace();
        return null;
        
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
            }
        }
        
    }

    @Override
    public ArrayList<Question> getAllQuestions() throws RemoteException {
        Statement statement = null;
        
        String sql = "select * from questions";
        
        try{
            statement = DB_Connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
           
            
            ArrayList<Question> list = new ArrayList<Question>();
            
            while(result.next()){
                Question updateques = new Question();
                
                String id = "1000";
                updateques.setId(id);
                
             //    updateques.setId(result.getString("id"));
               // updateques.setId(result.getString("id"));
                updateques.setQuestion(result.getString("question"));
                updateques.setOption1(result.getString("option1"));
                updateques.setOption2(result.getString("option2"));
                updateques.setOption3(result.getString("option3"));
                updateques.setOption4(result.getString("option4"));
                updateques.setAnswer(result.getString("answer"));
                list.add(updateques);
            }
            result.close();
            return list;
        }catch(Exception ex){
        ex.printStackTrace();
        return null;
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    

  

 

   
}
