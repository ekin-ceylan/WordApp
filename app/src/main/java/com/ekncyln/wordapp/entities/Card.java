package com.ekncyln.wordapp.entities;

public class Card {
    public int Id;
    public String Word;
    public String Meaning;
    public String Artikel;
    public String Sample;
    public String ImageSrc;

    public boolean WordSide = true;
    public boolean MeaningSide = false;
    public boolean ArtikelSide = false;
    public boolean SampleSide = false;
    public boolean ImageSide = true;

    public short Level;
    public int AskCount = 0;
    public int AnswerCount = 0;
}
