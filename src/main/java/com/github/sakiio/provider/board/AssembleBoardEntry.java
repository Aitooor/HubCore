package com.github.sakiio.provider.board;

import org.bukkit.*;
import org.bukkit.scoreboard.*;

public class AssembleBoardEntry
{
    private final AssembleBoard board;
    private String text;
    private final String identifier;
    private Team team;
    private int position;
    
    public AssembleBoardEntry(final AssembleBoard board, final String text, final int position) {
        this.board = board;
        this.setText(text);
        this.position = position;
        this.identifier = this.board.getUniqueIdentifier(position);
        this.setup();
    }
    
    public void setup() {
        final Scoreboard scoreboard = this.board.getScoreboard();
        if (scoreboard == null) {
            return;
        }
        String teamName = this.identifier;
        if (teamName.length() > 16) {
            teamName = teamName.substring(0, 16);
        }
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }
        if (team.getEntries() == null || team.getEntries().isEmpty() || !team.getEntries().contains(this.identifier)) {
            team.addEntry(this.identifier);
        }
        if (!this.board.getEntries().contains(this)) {
            this.board.getEntries().add(this);
        }
        this.team = team;
    }
    
    public void send(final int position) {
        if (this.getText().length() > 16) {
            String prefix = this.getText().substring(0, 16);
            String suffix;
            if (prefix.charAt(15) == '�') {
                prefix = prefix.substring(0, 15);
                suffix = this.getText().substring(15, this.getText().length());
            }
            else if (prefix.charAt(14) == '�') {
                prefix = prefix.substring(0, 14);
                suffix = this.getText().substring(14, this.getText().length());
            }
            else if (ChatColor.getLastColors(prefix).equalsIgnoreCase(ChatColor.getLastColors(this.identifier))) {
                suffix = this.getText().substring(16, this.getText().length());
            }
            else {
                suffix = String.valueOf(ChatColor.getLastColors(prefix)) + this.getText().substring(16, this.getText().length());
            }
            if (suffix.length() > 16) {
                suffix = suffix.substring(0, 16);
            }
            this.team.setPrefix(prefix);
            this.team.setSuffix(suffix);
        }
        else {
            this.team.setPrefix(this.getText());
            this.team.setSuffix("");
        }
        final Score score = this.board.getObjective().getScore(this.identifier);
        score.setScore(position);
    }
    
    public void remove() {
        this.board.getIdentifiers().remove(this.identifier);
        this.board.getScoreboard().resetScores(this.identifier);
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
}
