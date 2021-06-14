package states;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import ConnectionDB.Crud;
import gameObjects.Constants;
import graphics.Assets;
import graphics.Text;
import io.JSONParser;
import io.ScoreData;
import math.Vector2D;
import ui.Action;
import ui.Button;

import javax.swing.JOptionPane;

public class ScoreState extends State{
	
	private Button returnButton, deleteButton, updateButton;
	
	private PriorityQueue<ScoreData> highScores;
	
	private Comparator<ScoreData> scoreComparator;
	
	private ScoreData[] auxArray;
	
	public ScoreState() {
		returnButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				Constants.HEIGHT - Assets.greyBtn.getHeight() * 2,
				Constants.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);

		deleteButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				(Constants.HEIGHT - Assets.greyBtn.getHeight() * 2)-60,
				"Eliminar",
				new Action() {
					@Override
					public void doAction() {
						String equipo = JOptionPane.showInputDialog("Ingresa el nombre del equipo a eliminar");
						Crud.deleteEquipo(equipo);
					}
				}
		);

		updateButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				(Constants.HEIGHT - Assets.greyBtn.getHeight() * 2)-100,
				"Actualizar",
				new Action() {
					@Override
					public void doAction() {
						/*String nombre = JOptionPane.showInputDialog("Ingresa el nombre");
						String nuevoNombre = JOptionPane.showInputDialog("Ingresa el nuevo nombre");
						Crud.updateEquipo(nombre, nuevoNombre);*/
					}
				}
		);
		
		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
		
		try {
			ArrayList<ScoreData> dataList = JSONParser.readFile();
			
			for(ScoreData d: dataList) {
				highScores.add(d);
			}
			
			while(highScores.size() > 10) {
				highScores.poll();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(float dt) {
		returnButton.update();
		deleteButton.update();
		updateButton.update();
	}

	@Override
	public void draw(Graphics g) {
		returnButton.draw(g);
		deleteButton.draw(g);
		updateButton.draw(g);
		
		auxArray = highScores.toArray(new ScoreData[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);


		Vector2D teamPos = new Vector2D(
				Constants.WIDTH/2 - 400,
				100
		);

		Vector2D scorePos = new Vector2D(
				Constants.WIDTH / 2 - 200,
				100
				);

		Vector2D deathsPos = new Vector2D(
				Constants.WIDTH / 2 + 0,
				100
		);

		Vector2D datePos = new Vector2D(
				Constants.WIDTH / 2 + 200,
				100
				);


		Text.drawText(g, "TEAM", teamPos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, Constants.SCORE, scorePos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, "DEATHS", deathsPos, true, Color.BLUE, Assets.fontBig);
		Text.drawText(g, Constants.DATE, datePos, true, Color.BLUE, Assets.fontBig);
		
		scorePos.setY(scorePos.getY() + 40);
		datePos.setY(datePos.getY() + 40);

		for(ScoreData d: Crud.getData()) {

			Text.drawText(g, d.getNombre(), teamPos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, Integer.toString(d.getMuertes()), deathsPos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);

			teamPos.setY(teamPos.getY() + 40);
			scorePos.setY(scorePos.getY() + 40);
			deathsPos.setY(deathsPos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
		}

		
		/*for(int i = auxArray.length - 1; i > -1; i--) {
			
			ScoreData d = auxArray[i];

			Text.drawText(g, d.getNombre(), teamPos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, Integer.toString(d.getMuertes()), deathsPos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);

			teamPos.setY(teamPos.getY() + 40);
			scorePos.setY(scorePos.getY() + 40);
			deathsPos.setY(deathsPos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
			
		}*/
		
	}
	
}
