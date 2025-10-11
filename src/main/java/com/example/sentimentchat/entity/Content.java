package com.example.sentimentchat.entity;

import com.example.sentimentchat.enums.Sentiment;
import lombok.Data;

@Data
public class Content {
    private String text;
    private Sentiment sentiment;
}