package com.ekncyln.wordapp.entities;

import android.app.Application;

import java.util.ArrayList;

public class Store extends Application {
    public ArrayList<Set> Sets;

    public Store() {
        Card overeat = new Card(){{
                    Word = "overeat";
                    Meaning = "gereğinden fazla yemek";
                    Level = 7;
                    AskCount = 4;
                    AnswerCount = 4;
                }},
                punch = new Card(){{
                    Word = "punch";
                    Meaning = "aletle delmek";
                    Level = 4;
                    AskCount = 5;
                    AnswerCount = 3;
                }},
                excel = new Card(){{
                    Word = "excel";
                    Meaning = "bir işte üstün olmak";
                    Level = 7;
                    AskCount = 0;
                    AnswerCount = 0;
                    Sample = "She excelled in math.";
                }},
                plush = new Card(){{
                    Word = "plush";
                    Meaning = "pelüş";
                    Level = 7;
                    AskCount = 4;
                    AnswerCount = 4;
                }},
                fatal = new Card(){{
                    Word = "fatal";
                    Meaning = "ölümcül";
                    Level = 6;
                    AskCount = 4;
                    AnswerCount = 3;
                }},
                purport = new Card(){{
                    Word = "purport";
                    Meaning = "iddia etmek";
                    Level = 7;
                    AskCount = 4;
                    AnswerCount = 3;
                }},
                withstand = new Card(){{
                    Word = "withstand";
                    Meaning = "üstesinden gelmek";
                    Level = 7;
                }},
                breakApart = new Card(){{
                    Word = "break apart";
                    Meaning = "parçalara ayırmak";
                    Level = 7;
                }},
                prefer = new Card(){{
                    Word = "prefer";
                    Meaning = "bir şeyi veya bir kimseyi diğerine tercih etmek";
                    Level = 3;
                    Sample = "Some people prefer camping to staying in hotels.";
                }};

        Set verb = new Set(){{
            Title = "Verb";
            Cards = new ArrayList<Card>();
        }},
                adjective = new Set(){{
                    Title = "Adjective";
                    Cards = new ArrayList<Card>();
                }};

        verb.Cards.add(overeat);
        verb.Cards.add(punch);
        verb.Cards.add(excel);
        verb.Cards.add(purport);
        verb.Cards.add(withstand);
        verb.Cards.add(breakApart);
        verb.Cards.add(prefer);

        adjective.Cards.add(plush);
        adjective.Cards.add(fatal);

        this.Sets = new ArrayList<Set>();
        this.Sets.add(verb);
        this.Sets.add(adjective);
        this.Sets.add(verb);
        this.Sets.add(adjective);
        this.Sets.add(verb);
        this.Sets.add(adjective);
    }
}
