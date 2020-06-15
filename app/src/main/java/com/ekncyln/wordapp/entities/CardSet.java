package com.ekncyln.wordapp.entities;

import java.util.List;

public class CardSet {
    public String Title;
    public List<Card> Cards;

    public int GetExpertCount(){
        int expertCount = 0;

        for (Card card : this.Cards) {
            if (card.AskCount == card.AnswerCount && card.AskCount > 0) {
                expertCount++;
            }
        }

        return expertCount;
    }

    public int GetLearningCount(){
        int learningCount = 0;

        for (Card card : this.Cards) {
            if (card.AskCount > card.AnswerCount && card.AskCount > 0) {
                learningCount++;
            }
        }

        return learningCount;
    }
}
