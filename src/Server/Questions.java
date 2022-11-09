/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author Lakmina
 */
public class Questions implements java.io.Serializable{
    
    private ArrayList<String> Questions = new ArrayList<String>();
    private ArrayList<String> Option1 = new ArrayList<String>();
    private ArrayList<String> Option2 = new ArrayList<String>();
    private ArrayList<String> Option3 = new ArrayList<String>();
    private ArrayList<String> Option4 = new ArrayList<String>();

    public ArrayList<String> getQuestions() {
        return Questions;
    }

    public void setQuestions(ArrayList<String> Questions) {
        this.Questions = Questions;
    }

    public ArrayList<String> getOption1() {
        return Option1;
    }

    public void setOption1(ArrayList<String> Option1) {
        this.Option1 = Option1;
    }

    public ArrayList<String> getOption2() {
        return Option2;
    }

    public void setOption2(ArrayList<String> Option2) {
        this.Option2 = Option2;
    }

    public ArrayList<String> getOption3() {
        return Option3;
    }

    public void setOption3(ArrayList<String> Option3) {
        this.Option3 = Option3;
    }

    public ArrayList<String> getOption4() {
        return Option4;
    }

    public void setOption4(ArrayList<String> Option4) {
        this.Option4 = Option4;
    }

   

    

    }

