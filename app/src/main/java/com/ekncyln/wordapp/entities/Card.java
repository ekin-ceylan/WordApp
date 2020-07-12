package com.ekncyln.wordapp.entities;

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
    public int AskCount = 0;
    public int AnswerCount = 0;
}
