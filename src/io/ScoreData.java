package io;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreData {

	private String nombre;
	private int score;
	private int muertes;
	private String date;

	public ScoreData(int score) {
		this.score = score;

		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.format(today);

	}

	public ScoreData(String nombre, int score, int muertes) {

		this.nombre = nombre;
		this.score = score;
		this.muertes = muertes;

		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.format(today);

	}

	public ScoreData() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMuertes() {
		return muertes;
	}

	public void setMuertes(int muertes) {
		this.muertes = muertes;
	}
}
