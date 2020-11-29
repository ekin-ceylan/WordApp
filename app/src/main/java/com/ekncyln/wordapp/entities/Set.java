package com.ekncyln.wordapp.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Set implements Serializable {
    public String Key;
    public String Title;
    public ArrayList<Card> Cards;

    public Set() {
        this.Cards = new ArrayList<Card>();
    }
    public int ExpertPercentage = 0;
    public int LearningPercentage = 0;

    public void CalcCompetence(){
        this.ExpertPercentage = 0;
        this.LearningPercentage = 0;

        if (this.Cards.size() == 0){
            return;
        }

        for (Card card : this.Cards) {
            if (card.getLearningStatus() == LearningStatus.LEARNT) {
                this.ExpertPercentage++;
            }
            else if (card.getLearningStatus() == LearningStatus.LEARNING) {
                this.LearningPercentage++;
            }
        }

        this.ExpertPercentage = this.ExpertPercentage * 100/this.Cards.size();
        this.LearningPercentage = this.LearningPercentage * 100/this.Cards.size();
    }
}
