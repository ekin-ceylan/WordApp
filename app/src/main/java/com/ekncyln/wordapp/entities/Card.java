package com.ekncyln.wordapp.entities;

import java.util.ArrayList;

public class Card {
    public String Key;
    public String Word;
    public String Meaning;
    public String Artikel;
    public String Sample;
    public String ImageSrc;
    public String SetName;

    public boolean ArtikelSide = false;
    public boolean SampleSide = false;
    public boolean ImageSide = true;

    public int Level;
    public ArrayList<Boolean> Answers;

    public Card() {
        this.Answers = new ArrayList<Boolean>();
    }

    public LearningStatus getLearningStatus(){
        int count = this.Answers.size();

        if (count == 0){
            return LearningStatus.NODATA;
        }else if (count < 5){
            return LearningStatus.LEARNING;
        }

        for (int i = count - 5; i < count; i++) {
            if(!this.Answers.get(i)){
                return LearningStatus.LEARNING;
            }
        }

        return LearningStatus.LEARNT;
    }

    public int getPerformance(){
        int count = this.Answers.size();

        if (count == 0)
            return 0;

        int correct = 0;

        for (boolean answer: this.Answers) {
            if (answer)
                correct++;
        }

        return correct * 100 / count;
    }

    public int getRecentPerformance(){
        int count = this.Answers.size();

        if (count == 0)
            return 0;

        int recents = Math.min(count, 5);
        int correct = 0;

        for (int i = count - recents; i < count; i++) {
            if(this.Answers.get(i)){
                correct++;
            }
        }

        return correct * 100 / recents;
    }

}
