/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Jnorri
 */
public class SecurityQuestion {
    
    int userID;
    String question;
    String answer;

    public SecurityQuestion(int userID, String question, String answer) {
        this.userID = userID;
        this.question = question;
        this.answer = answer;
    }
    
    public SecurityQuestion() {
        userID = 0;
        question = "";
        answer = "";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }   
        
}
