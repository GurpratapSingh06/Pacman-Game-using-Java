# Pacman-Game-using-
🟡 Pac-Man Game
A classic Pac-Man game implementation in Java using Swing GUI with intelligent ghost AI, smooth animations, and professional UI.

Game Status
Java
Platform

📋 Table of Contents
Features

Demo

Installation

How to Run

Controls

Game Mechanics

Project Structure

Technical Details

Screenshots

Learning Outcomes

Future Enhancements

Contributing

Author

License

✨ Features
🎮 Classic Arcade Gameplay - Authentic Pac-Man experience

👻 4 Intelligent Ghosts - BLINKY (Red), PINKY (Pink), INKY (Cyan), CLYDE (Orange)

🧠 Smart AI - Ghost path-finding using Manhattan distance algorithm

🗺️ 19x19 Maze Layout - Classic maze design with 361 tiles

💊 Power Pellets - Make ghosts vulnerable and edible

📊 Scoring System - 10 pts (pellet), 50 pts (power), 200 pts (ghost)

❤️ 3 Lives System - Multiple chances to win

🎨 Professional UI - Tutorial page, loading screen, game over screen

🎬 Smooth Animations - Animated Pac-Man mouth movement

🎯 Win/Lose Conditions - Collect all pellets to win

🎥 Demo
Gameplay Flow:
Tutorial Page → Shows controls and objectives

Loading Screen → Animated progress bar (3 seconds)

Game Screen → Play the game!

Game Over → Show final score & restart option

📦 Installation
Prerequisites
Java JDK 8 or higher

No external dependencies (uses built-in Java Swing library)

Clone Repository
bash
git clone https://github.com/your-username/PacmanGame.git
cd PacmanGame
🚀 How to Run
Method 1: Command Line
Navigate to project directory:

bash
cd PacmanGame/src
Compile:

bash
javac pacmangame/*.java
Run:

bash
java pacmangame.PacmanGame
Method 2: Eclipse IDE
Open Eclipse

File → Open Projects from File System

Select the PacmanGame folder

Right-click PacmanGame.java → Run As → Java Application

Method 3: VS Code
Open VS Code

Open the PacmanGame folder

Install "Extension Pack for Java"

Press F5 to run

🎮 Controls
Key	Action
← Left Arrow	Move Left
→ Right Arrow	Move Right
↑ Up Arrow	Move Up
↓ Down Arrow	Move Down
SPACE	Start Game / Restart
🎯 Game Mechanics
Objective
Collect all white pellets in the maze while avoiding 4 colored ghosts. Eat power pellets (larger dots in corners) to make ghosts vulnerable and gain the ability to eat them!

Scoring
Regular Pellet: 10 points

Power Pellet: 50 points

Eating Ghost: 200 points

Lives
Start with 3 lives

Lose a life when caught by a ghost

Game over when all lives are lost

Win Condition
Collect all pellets in the maze

Lose Condition
Lose all 3 lives

Power-Up Mechanics
Eat a power pellet to make ghosts turn blue for ~5 seconds

During this time, you can eat ghosts for bonus points

Eaten ghosts respawn at the center ghost house

📁 Project Structure
text
PacmanGame/
├── src/
│   └── pacmangame/
│       ├── PacmanGame.java      # Main window/frame
│       ├── GamePanel.java       # Game logic & rendering
│       ├── Pacman.java          # Player character
│       ├── Ghost.java           # Enemy AI & behavior
│       └── Maze.java            # Level design (19x19 grid)
├── README.md
└── .gitignore
Class Descriptions
Class	Description
PacmanGame.java	Main entry point, creates the JFrame window
GamePanel.java	Core game logic, state management, rendering, event handling
Pacman.java	Player character properties (position, direction)
Ghost.java	Ghost AI, color, vulnerable state, movement logic
Maze.java	19x19 level layout with walls, pellets, power pellets
🛠️ Technical Details
Technologies
Language: Java (JDK 8+)

GUI Framework: Swing (javax.swing)

Graphics: 2D Graphics API (java.awt)

Design Pattern: Object-Oriented Programming (OOP)

Game Loop
Update Cycle: 100ms (10 updates per second)

Rendering: 60+ FPS for smooth visuals

Ghost Movement: Every 3 game ticks (balanced difficulty)

Ghost AI Algorithm
Calculate Manhattan distance to Pac-Man

Move toward closer axis (X or Y)

Check walls before moving

Random fallback if path is blocked

State Management
TUTORIAL → Shows game instructions

LOADING → Animated loading screen

PLAYING → Active gameplay

GAME_OVER → Final score display

Collision Detection
Grid-based collision system

Real-time position checking

Separate logic for pellets and ghosts

📸 Screenshots
Tutorial Page
text
┌─────────────────────────────────┐
│         PAC-MAN GAME            │
│                                 │
│   Controls:                     │
│   • Arrow Keys - Move           │
│   • Space - Restart             │
│                                 │
│   Objective:                    │
│   • Collect all pellets         │
│   • Avoid ghosts!               │
│                                 │
│   Press SPACE to Continue       │
└─────────────────────────────────┘
Game Screen
text
┌─────────────────────────────────┐
│ ████ .... ████ .... ████        │
│ .... 👻  .... 👻  ....         │
│ ████ .... ████ .... ████        │
│ .... 🟡  ....  .... ....        │
│ ████ .... ████ .... ████        │
│                                 │
│ Score: 1200    Lives: ❤❤❤      │
└─────────────────────────────────┘
📚 Learning Outcomes
Programming Concepts
✅ Object-Oriented Programming - Classes, inheritance, encapsulation
✅ Game Development - Game loops, state management, timing
✅ Graphics Programming - 2D rendering, animations, double buffering
✅ AI Implementation - Path-finding, decision-making algorithms
✅ Event Handling - Keyboard input, action listeners
✅ Collision Detection - Grid-based collision systems

Software Engineering
✅ Code Organization - Modular design, separation of concerns
✅ Debugging - Problem-solving, bug fixing
✅ Version Control - Git workflow, repository management
✅ Documentation - Code comments, README files

🚀 Future Enhancements
Planned Features
🔊 Sound Effects - Pellet collection, ghost eaten sounds

🎵 Background Music - Classic Pac-Man theme

🏆 Highscore System - Persistent score storage using files

🎮 Multiple Levels - Increasing difficulty with level progression

🧩 Different Maze Layouts - Variety of level designs

⏸️ Pause Feature - Pause and resume gameplay

👥 Multiplayer Mode - Local or network multiplayer

🎨 Better Graphics - Enhanced sprites and animations

📊 Statistics - Track gameplay metrics

🌙 Dark/Light Theme - UI customization options

🤝 Contributing
Contributions are welcome! Here's how you can help:

Fork the repository

Create a new branch (git checkout -b feature/YourFeature)

Commit your changes (git commit -m 'Add some feature')

Push to the branch (git push origin feature/YourFeature)

Open a Pull Request

Code Guidelines
Follow Java naming conventions

Add comments for complex logic

Test before submitting PR

Update README if needed


Project Details

📅 Date: November 2025

🏫 Course: Computer Graphics Mini Project


📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

text
MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
🙏 Acknowledgments
Original Pac-Man game by Namco (1980)

Java Swing documentation and community

Stack Overflow for troubleshooting help

Perplexity AI for development assistance


⭐ Star This Repository
If you found this project helpful, please give it a ⭐ on GitHub!

Made with ❤️ for Computer Graphics Course

🎮 Happy Gaming! 👻🟡
