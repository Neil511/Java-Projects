package blackjack;

//Neil Lohana
//Date: June 12, 2012
//Turner Fenton Secondary School
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BlackJack extends Applet implements MouseListener
{

  int screen;  //Global variable to hold what screen is to be displayed
  /*
  0 Title
  1 Instructions
  2 Game
  3 Credits
  */

  Image TitleScreen, GameScreen, InstructionsScreen, CreditsScreen, PlayRoll, RedCardFace, BlueCardFace, InCard1, InCard2, HitCard1, HitCard2, HitCard3, CompCard1, CompCard2; //Number Images for Display
  //  1 - 13 Spadess 14 - 26 Clubs  27 - 39 Hearts 39 - 52 Diamonds
  int cards[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, };
  int initcard1, initcard2, cpucard1, cpucard2, cpuvalue, hit1, hit2, hit3;
  //int initcard1 = (int) (Math.random () * 52) + 1;
  //int initcard2 = (int) (Math.random () * 52) + 1;

  int hit = 0;
  int random = 0; // 5 = first two cards drawn, 10 = new game, 15 = cpu cards flipped
  int cardvalue1 = cards [initcard1] + cards [initcard2] + cards [hit1] + cards [hit2] + cards [hit3];
  int betamount = 0;
  int money = 1000;
  int F2Cards = 0;
  int PrintSeven = 0;
  int PrintCpuCards = 0;
  String winlose = "Poo";
  String name;

  //****Note to self: If execution fails, reassure that all images are accounted for within the same folder
  public void init ()
  {
      InitCards ();
      resize (900, 600);
      screen = 0;  //Start screen 0 , titlescreen
      addMouseListener (this);

      //Paint Title Screen picture
      String TS = "Screens/TitleScreen.jpg ";
      TitleScreen = getImage (getDocumentBase (), TS);
      if (TitleScreen == null)
          System.exit (0);

      //Paint Instructions Screen picture
      String IS = "Screens/InstructionsScreen.jpg ";
      InstructionsScreen = getImage (getDocumentBase (), IS);
      if (InstructionsScreen == null)
          System.exit (0);

      //Get the GameScreen Picture
      String GS = "Screens/GameScreen.jpg ";
      GameScreen = getImage (getDocumentBase (), GS);
      if (GameScreen == null)
          System.exit (0);

      //Get the Credits Screen Picture
      String CS = "Screens/CreditsScreen.jpg ";
      CreditsScreen = getImage (getDocumentBase (), CS);
      if (CreditsScreen == null)
          System.exit (0);

      //Get face down cards
      String RCF = "GameCards/RedCardFace.jpg ";
      RedCardFace = getImage (getDocumentBase (), RCF);
      if (RedCardFace == null)
          System.exit (0);

      String BCF = "GameCards/BlueCardFace.jpg";
      BlueCardFace = getImage (getDocumentBase (), BCF);
      if (BlueCardFace == null)
          System.exit (0);

      // Get the user's name
      name = JOptionPane.showInputDialog ("Please enter your name");

      Graphics g = getGraphics ();

  }

  public void InitCards ()
  {
      initcard1 = (int) (Math.random () * 52) + 1;
      initcard2 = (int) (Math.random () * 52) + 1;
      cardvalue1 = cards [initcard1] + cards [initcard2] + cards [hit1] + cards [hit2] + cards [hit3];

      String IC1 = ("GameCards/" + initcard1 + ".jpg");
      InCard1 = getImage (getDocumentBase (), IC1);
      if (InCard1 == null)
          System.exit (0);

      String IC2 = ("GameCards/" + initcard2 + ".jpg");
      InCard2 = getImage (getDocumentBase (), IC2);
      if (InCard1 == null)
          System.exit (0);
      cpucard1 = 10;
      cpucard2--;
      cpuvalue = (cpucard1 + cpucard2);
      repaint ();
  }


  public void CpuCards ()
  {
      cpucard1 = (int) (Math.random () * 16) + 1;
      cpucard2 = (int) (Math.random () * 4) + 7;


      //String CC1 = ("CpuCards/GameCards/" + cpucard1 + "c.jpg");
      String CC1 = ("GameCards/" + cpucard1 + "c.jpg");
      CompCard1 = getImage (getDocumentBase (), CC1);
      if (CompCard1 == null)
          System.exit (0);

      String CC2 = ("GameCards/" + cpucard2 + ".jpg");
      CompCard2 = getImage (getDocumentBase (), CC2);
      if (CompCard2 == null)
          System.exit (0);

      cpucard1 = 10;

      if (cards [cpucard2] > 10)
          cpucard2 = 10;

      cpuvalue = (cpucard1 + cpucard2);
      showStatus (" " + cpucard1 + " " + cpucard2);

  }


  public void Hit ()
  { //Declaring Number and Image for Hit 1

      if (hit == 1)
      {
          int hit1 = (int) (Math.random () * 52) + 1;
          String HC1 = ("GameCards/" + hit1 + ".jpg");
          HitCard1 = getImage (getDocumentBase (), HC1);
          cardvalue1 += cards [hit1];
          loseTest();

          if (HitCard1 == null)
              System.exit (0);
      }
      //Declaring Number and Image for Hit 2
      else if (hit == 2)
      {
          int hit2 = (int) (Math.random () * 52) + 1;
          String HC2 = ("GameCards/" + hit2 + ".jpg");
          HitCard2 = getImage (getDocumentBase (), HC2);
          cardvalue1 += cards [hit2];
          loseTest();
          
          if (HitCard2 == null)
              System.exit (0);
      }
      //Declaring Number and Image for Hit 3
      else if (hit == 3)
      {
          int hit3 = (int) (Math.random () * 52) + 1;
          String HC3 = ("GameCards/" + hit3 + ".jpg");
          HitCard3 = getImage (getDocumentBase (), HC3);
          cardvalue1 += cards [hit3];
          loseTest();
          
          if (HitCard3 == null)
              System.exit (0);
      }

      repaint ();

  }


  public void WinTest ()
  {
      if (cardvalue1 > 21)
      {
          //PrintSeven = 1 ;
          showStatus ("You busted!");
          betamount = 0;
          PrintCpuCards = 1;
          winlose = "Loss";
          //money -= 50;
          if (money < 0)
              money = 0;

          repaint ();

      }
      else if (cardvalue1 > cpuvalue)
      {
          showStatus ("You win!");
          money += betamount * 2;
          PrintCpuCards = 1;
          winlose = "Win";
          money += 50;
          repaint ();

      }
      else if (cardvalue1 == cpuvalue)
      {
          showStatus ("You win!");
          money += betamount * 2;
          PrintCpuCards = 1;
          winlose = "Win";
          money += 50;
          repaint ();

      }
      else
      {
          showStatus ("You lose!");
          betamount = 0;
          PrintCpuCards = 1;
          winlose = "Loss";
          //money -= 50;
          if (money < 0)
              money = 0;

          repaint ();

      }
      if (money <= 0)
      {
          //Show an error dialog that displays the message, 'alert':
          JOptionPane.showMessageDialog (null, "You lost all your money, the game will now exit.", "Loser!",
                  JOptionPane.ERROR_MESSAGE);
          System.exit (0);

      }
  }


  public void loseTest ()
  {
  if (cardvalue1 > 21)
      {
          //PrintSeven = 1 ;
          showStatus ("You busted!");
          betamount = 0;
          PrintCpuCards = 1;
          winlose = "Loss";
          //money -= 50;
          if (money < 0)
              money = 0;

          repaint ();

      }
  }


  public void mouseClicked (MouseEvent e)
  {
  }


  public void mouseEntered (MouseEvent e)
  {
  }


  public void mouseExited (MouseEvent e)
  {
  }


  public void mouseReleased (MouseEvent e)
  {
  }


  public void mousePressed (MouseEvent e)
  {
      //Get x and y co-ordinates of the mouse
      int x = e.getX ();
      int y = e.getY ();

      // ******Main Screen Buttons*****
      if (screen == 0)
      {
          if (x > 61 && x < 332 && y > 421 && y < 491) // Instructions Screen
          {
              screen = 1;
              repaint ();
          }

          if (x > 61 && x < 163 && y > 345 && y < 416) // Games Screen
          {
              screen = 2;
              repaint ();
          }

          if (x > 61 && x < 222 && y > 498 && y < 570) // Credits Screen
          {
              screen = 3;
              repaint ();
          }

      }


      //*****Instructions Screen Buttons*****
      if (screen == 1)
      {
          if (x > 14 && x < 127 && y > 542 && y < 589) // Back Button on IS

              { // Sends back to Title
                  screen = 0;
                  repaint ();
              }

      }


      //*****Game Screen Buttons*****
      if (screen == 2)
      {
          //Back Button
          if (x > 428 && x < 490 && y > 312 && y < 348) // Back Button on IS

              {
                  screen = 0;
                  repaint ();
              }
          if ((x > 326 && x < 580 && y > 258 && y < 305) && (hit == 0) && (F2Cards == 0))
          { // Casino Royale Button
              //screen = 2;
              InitCards ();
              F2Cards = 1;
              repaint ();
              PrintSeven = 0;

          }
          //New Game Button
          if ((x > 326 && x < 420 && y > 312 && y < 348) && (PrintCpuCards == 1))
          { //Redraws face down cards for red
              PrintSeven = 1;
              PrintCpuCards = 0;
              F2Cards = 0;
              hit = 0;
              betamount = 0;
              winlose = "Poo";
              repaint ();
          }
          //Submit Cards Button
          if ((x > 495 && x < 580 && y > 312 && y < 348) && (F2Cards == 1) && (PrintCpuCards == 0))
          {
              PrintCpuCards = 1;
              CpuCards ();
              WinTest ();
              repaint ();

              // hit = 0 ;
          }
          //Hit Card 1
          if ((x > 386 && x < 520 && y > 20 && y < 235) && (F2Cards == 1))
          {
              hit++;
              Hit ();
              repaint ();

          }
          //Hit Card 2
          if ((x > 555 && x < 690 && y > 20 && y < 235) && (F2Cards == 1))
          {
              hit++;
              Hit ();
              repaint ();
          }
          //Hit Card 3
          if ((x > 727 && x < 860 && y > 20 && y < 235) && (F2Cards == 1))
          {
              hit++;
              Hit ();
              repaint ();
          }
          //Betting Chips
          //Yellow
          if ((x > 375 && x < 485 && y > 450 && y < 540) && (money > 0))
          {
              betamount += 1;
              money -= 1;
              repaint ();
          }
          //Green
          if ((x > 500 && x < 610 && y > 450 && y < 540) && (money > 9))
          {
              betamount += 10;
              money -= 10;
              repaint ();
          }
          //Red
          if ((x > 620 && x < 735 && y > 450 && y < 540) && (money > 49))
          {
              betamount += 50;
              money -= 50;
              repaint ();
          }
          //Blue
          if ((x > 745 && x < 855 && y > 450 && y < 540) && (money > 99))
          {
              betamount += 100;
              money -= 100;
              repaint ();
          }
          //Secret
          if (x > 185 && x < 205 && y > 560 && y < 580)
          {
              money += 9999;
              repaint ();
          }
          //Bet All Buttom
          /*if (x > 550 && x < 600 && y > 435 && y < 460)
          {
              betamount += money;
              money = 0;
              repaint ();
          }*/
          /*if ((x > 0 && x < 900 && y > 0 && y < 600) && (winlose.equals ("End")))
          {
              System.exit (0);
          }
          */

          //*****Credits Screen Buttons*****

      }
      if (screen == 3)
      {
          if (x > 26 && x < 92 && y > 538 && y < 582) // Back Button on CS

              { // Sends back to Title
                  screen = 0;
                  repaint ();
                  showStatus ("Hi");
              }
      }

  }


  public void update (Graphics g)
  { //Overide the regular update method so it doesn't clear the screen before it draws
      paint (g);
  }


  public void paint (Graphics g)
  {
      if (screen == 0) //Title
      {
          g.drawImage (TitleScreen, 0, 0, this);
          showStatus ("Welcome " + name + " to Casino Royale");
      }


      else if (screen == 1) //Instructions
      {
          g.drawImage (InstructionsScreen, 0, 0, this);
      }


      else if (screen == 2)
          //Game Screen
          {

              g.drawImage (GameScreen, 0, 0, this);



              //Paint Money and Betamount
              g.setColor (Color.red);
              g.setFont (new Font ("Showcard Gothic", Font.BOLD, 25));
              g.drawString ("Bet: " + betamount, 700, 400);
              g.setColor (Color.green);
              g.setFont (new Font ("Showcard Gothic", Font.BOLD, 25));
              g.drawString ("Money: " + money, 500, 400);
              /*g.setColor (Color.white);
              g.setFont (new Font ("Showcard Gothic", Font.BOLD, 25));
              g.drawString ("Bet All! ", 550, 435); */
              //Paint Face Down Cards for Red
              g.drawImage (RedCardFace, 45, 20, this);
              g.drawImage (RedCardFace, 214, 20, this);
              g.drawImage (RedCardFace, 386, 20, this);
              g.drawImage (RedCardFace, 555, 20, this);
              g.drawImage (RedCardFace, 727, 20, this);

              //Paint Face Down Cards for Blue
              g.drawImage (BlueCardFace, 45, 367, this);
              g.drawImage (BlueCardFace, 214, 367, this);

              if (hit >= 1)
              {
                  g.drawImage (HitCard1, 386, 20, this);
                  //  g.drawString (" " + cardvalue1, 730, 325);
              }
              if (hit >= 2)
              {
                  g.drawImage (HitCard2, 555, 20, this);
                  //  g.drawString (" " + cardvalue1, 730, 325);
              }
              if (hit >= 3)
              {
                  g.drawImage (HitCard3, 727, 20, this);
                  // g.drawString (" " + cardvalue1, 730, 325);
              }

              //Painting First Two Cards on
              if (F2Cards == 1)
              {
                  //g.setColor (Color.white);
                  g.setColor (new Color (147, 167, 162));
                  g.setFont (new Font ("Times New Roman", Font.BOLD, 25));
                  g.drawString ("Card Value: " + cardvalue1, 600, 325);

                  g.drawImage (InCard1, 45, 20, this);
                  g.drawImage (InCard2, 214, 20, this);

              }
              if (PrintSeven == 1)
              {
                  g.drawImage (RedCardFace, 45, 20, this);
                  g.drawImage (RedCardFace, 214, 20, this);
                  g.drawImage (RedCardFace, 386, 20, this);
                  g.drawImage (RedCardFace, 555, 20, this);
                  g.drawImage (RedCardFace, 727, 20, this);

                  g.drawImage (BlueCardFace, 45, 367, this);
                  g.drawImage (BlueCardFace, 214, 367, this);

                  hit = 0;
                  PrintSeven = 0;
              }
              if (PrintCpuCards == 1)
              {
                  g.drawImage (CompCard1, 45, 367, this);
                  g.drawImage (CompCard2, 214, 367, this);


                  g.setColor (new Color (147, 167, 162));
                  g.setFont (new Font ("Times New Roman", Font.BOLD, 25));
                  g.drawString ("Card Value: " + cpuvalue, 50, 325);
              }
              if (winlose.equals ("Loss"))
              {
                  g.setFont (new Font ("Showcard Gothic", Font.BOLD, 100));
                  g.setColor (Color.red);
                  g.drawString ("You Lost!", 200, 325);
              }
              else if (winlose.equals ("Win"))
              {
                  g.setFont (new Font ("Showcard Gothic", Font.BOLD, 100));
                  g.setColor (Color.green);
                  g.drawString ("You Win!", 200, 325);
              }
              else if (winlose.equals ("Lose Game"))
              {
                  g.setFont (new Font ("Showcard Gothic", Font.BOLD, 75));
                  g.setColor (Color.red);
                  g.drawString ("You Lose the Game!", 50, 325);
              }
              else
              {
                  g.drawString (" ", 0, 325);
              }
          }


      else if (screen == 3) //Credits
      {
          g.drawImage (CreditsScreen, 0, 0, this);
      }
  }
}


