package com.ekncyln.wordapp.entities;

import java.util.List;

public class Set {
    public int Id;
    public String Title;
    public List<Card> Cards;

    public int GetExpertCount(){
        if (this.Cards.size() == 0)
            return 0;

        int expertCount = 0;

        for (Card card : this.Cards) {
            if (card.AskCount == card.AnswerCount && card.AskCount > 0) {
                expertCount++;
            }
        }

        return expertCount * 100/this.Cards.size();
    }

    public int GetLearningCount(){
        if (this.Cards.size() == 0)
            return 0;

        int learningCount = 0;

        for (Card card : this.Cards) {
            if (card.AskCount > card.AnswerCount && card.AskCount > 0) {
                learningCount++;
            }
        }

        return learningCount * 100/this.Cards.size();
    }
}
