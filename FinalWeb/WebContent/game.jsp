<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICode Games</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrapBody.css" rel="stylesheet" type="text/css"/>
 <link rel="stylesheet" href="jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="scripts/demos.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxtabs.js"></script>
    <script type="text/javascript" src="jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //Creating jqxTabs
            $('#jqxTabs').jqxTabs({ width: 500,  selectionTracker: true, animationType: 'fade' });
        });
    </script>
    <style type="text/css">
        .big-image
        {
            float: left;
            margin-right: 15px;
            margin-bottom: 15px;
            border: 1px solid #999;
            background: #fff;
            padding: 3px;
        }
        .small-image
        {
            border: 1px solid #ccc;
            background: #fff;
            padding: 3px;
        }
        .content-container
        {
            padding-left: 15px;
            padding-top: 15px;
            padding-right: 15px;
        }
        .important-text
        {
            font-size: 13px;
            color: #000;
        }
        .more-text
        {
            color: #444;
            font-size: 11px;
            font-style: italic;
        }
        div#body
        {
			text-align:left;
			font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;
		}
    </style>
</head>
<body>
<div id='body'>
	<jsp:include page="includes/nav.jsp"/>

<h1 align="center">ICode Games Portal</h1>
<div id='jqxWidget'>
<table align="center">
<tr>
<td>
        <div style="float: center">
            <div id='jqxTabs' class='jqx-rc-all'>
                <ul style="margin-left: 40px;" id="unorderedList">
               		<li>
                        <div style="height: 40px;">
                            <img style='float: left;' width='32' height='32' src="gamePic/ICode Game.png" alt="" class="small-image" />
                            <div style="float: left; margin-left: 6px; text-align: center; margin-top: 5px; font-size: 13px;">
                                ICode Game
                                <br />
                                Game</div>
                        </div>
                    </li>
                    <li>
                        <div style="height: 40px;">
                            <img style='float: left;' width='32' height='32' src="gamePic/snakeGame.jpg" alt="" class="small-image" />
                            <div style="float: left; margin-left: 6px; text-align: center; margin-top: 5px; font-size: 13px;">
                                Snake<br/>
                                Game
                                </div>
                        </div>
                    </li>
                    <li>
                        <div style="height: 40px;">
                            <img style='float: left;' width='32' height='32' src="gamePic/sudoku.gif" alt="" class="small-image" />
                            <div style="float: left; margin-left: 6px; text-align: center; margin-top: 5px; font-size: 13px;">
                                Sudoku<br/>
                                Game</div>
                        </div>
                    </li>
                    <li>
                        <div style="height: 40px;">
                            <img style='float: left;' width='32' height='32' src="gamePic/tictactoe.png" alt="" class="small-image" />
                            <div style="float: left; margin-left: 6px; text-align: center; margin-top: 5px; font-size: 13px;">
                                Tic Tac Toe
                                <br />
                                Game</div>
                        </div>
                    </li>
                </ul>
                <div class="content-container">
                    <div style="height: 350px">
                        <img src="gamePic/ICode Game.png" alt="" class="big-image" />
                        <h3>
                           	ICode Game</h3>
                        <p class="important-text">
                            A Game specially designed for the event, ICode. 
                        </p>
                        <div class="more-text" align="center">
                            <a href="ICodeDG.jsp"><input type="button" value="Play Here"></a>
                        </div>
                    </div>
                </div>
                <div class="content-container">
                    <div style="height: 350px">
                        <img src="gamePic/snakeGame.jpg" alt="" class="big-image" />
                        <h3>
                            Snake Game</h3>
                        <p class="important-text">
                            How to Play Snake ?<br>
                            The Snake concept comes in two major variants:
In the first, which is most often a two-player game, there are multiple snakes on the playfield. Each player attempts to block the other so he or she runs into an existing trail and loses. Surround for the Atari 2600 is an example of this type. The Light Cycles segment of the Tron arcade game is a single-player version, where the other "snakes" are AI controlled.
In the second variant, a sole player attempts to eat objects by running into them with the head of the snake. Each object eaten makes the snake longer, so maneuvering is progressively more difficult. Examples: Nibbler, Snake Byte.
                        </p>
                        <div class="more-text" align="center"> 
                            <a href="snake.jsp"><input type="button" value="Play Here"></a>
                        </div>
                    </div>
                </div>
                <div class="content-container">
                    <div style="height: 350px">
                        <img src="gamePic/sudoku.gif" alt="" class="big-image" />
                        <h3>
                            Sudoku Game</h3>
                        <p class="important-text">
                         	Start at number 1. Use the same logic from an easy puzzle to fill in every empty box with all of the possible numbers for that box. If possible, put in definites. For example, picture three (above) shows that you can't solve for 3.
In a hard Sudoku, you won't be able to solve from the start, so just fill in what you know it could be. This will help later when you have two or three options per box and you can't remember what they are..<br/><a href="http://www.wikihow.com/Solve-a-Sudoku" target="_blank">More Info..</a>
                        </p>
                        <div class="more-text" align="center">
                            <a href="sudoku.jsp"><input type="button" value="Play Here"></a>
                        </div>
                    </div>
                </div>
                <div class="content-container">
                    <div style="height: 350px">
                        <img src="gamePic/tictactoe.png" alt="" class="big-image" />
                        <h3>
                            Tic Tac Toe</h3>
                        <p class="important-text">
                            Tic-tac-toe (or Noughts and crosses, Xs and Os) is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal row wins the game.
                        </p>
                        <div class="more-text" align="center">
                            <a href="tictactoe.jsp"><input type="button" value="Play Here"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div></td></tr></table>
	<jsp:include page="includes/footer.html"/>
</body>
</html>