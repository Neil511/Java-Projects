package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Snake snake = Snake.snake;

		g.setColor(Color.BLACK);

		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.WHITE);

		for (Point point : snake.snakeParts) {
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,
					Snake.SCALE, Snake.SCALE);
		}

		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
				Snake.SCALE, Snake.SCALE);

		g.setColor(Color.RED);

		g.fillOval(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,
				Snake.SCALE, Snake.SCALE);

		String Display = "Score: " + snake.score + ", Length: "
				+ snake.tailLength + ", Time: " + snake.time / 20;

		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString(Display, (int) (getWidth() / 2 - Display.length() * 2.5f),
				20);

		Display = "Arrow Keys to Move, Space to toggle pause, Start by selecting a speed '1', '2', '3'";

		if (snake.instructions) {
			g.drawString(Display,
					(int) (getWidth() / 2 - Display.length() * 2.5f - 120),
					(int) snake.dim.getHeight() / 4);
		}
		
		Display = "Game Over!";

		if (snake.over) {
			g.drawString(Display,
					(int) (getWidth() / 2 - Display.length() * 2.5f),
					(int) snake.dim.getHeight() / 4);
		}

		Display = "Paused!";

		if (snake.paused && !snake.over && !snake.instructions) {
			g.drawString(Display,
					(int) (getWidth() / 2 - Display.length() * 2.5f),
					(int) snake.dim.getHeight() / 4);
		}
	}
}
