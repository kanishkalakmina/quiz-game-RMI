/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Lakmina
 */

/*
# RMI Interface which consist with abstract methods to exchange the data
in between the server and the client.

#This is the parent class of the server side
*/
public interface RemoteInterface extends Remote{
    
    public String LoginForm(String email, String password) throws RemoteException;
    public String RegisterForm(String username,String email,String password, String confirm_password) throws RemoteException;
    public String AdminForm(String question,String option_1,String option_2,String option_3, String option_4,String answer) throws RemoteException;
    public String AdminFormupdate(String question,String option_1,String option_2,String option_3, String option_4,String answer) throws RemoteException;
    public String SearchValuesForm(String id)throws RemoteException;
    public ArrayList<Questions> getQuestions()throws RemoteException;
    public String UpdateQues(Question updateques)throws RemoteException;
    Question getQuestionById(String id)throws RemoteException;
    ArrayList<Question> getAllQuestions() throws RemoteException;
}
