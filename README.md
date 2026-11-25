
Java Factory Game

## Overview
Game created as part of university course. Every student was given a template of a suggested architecture and it was up to students to adapt or change it. No features were provided to students, so we had a
free will to add, change and design anything as we wanted. We were given a deadline of a month to design a fully functional game, record a 5 minute video were we presented the final product including functionality,
outstanding features and samples of code in requested parts.
## Features

- Tick-based simulation engine that progresses the game for each press of "Enter" key
- Building and resources autosave system
- Input simplification with provided help per request
- Input error handling to ensure robustness

## Tech Stack

- Language: Java
Skills: JUnit 5, CSV file handling, Bash / Shell scripting, Git and GitHub

## Architecture

/engine -  Simulation execution, state management and persistence
/model  -  Building abstractions, resource types and production behaviour
/parser -  Command interpretation and execution

The simulation separates buildings into three categories:
Producers: Generate raw materials.
Converters: Transform resources into manufactured goods.
Consumers: Exchange manufactured goods for credits.

Each simulation tick processes these categories sequentially before recording a snapshot of the resource inventory.

## Installation

### Prerequisites

Java Development Kit (JDK 17 or later recommended)
A compatible shell environment, such as Bash or Git Bash

### Setup

Clone the repository:
git clone https://github.com/DenD0p/Java-Game.git
cd Java-Game
bash run.sh

### Usage


## Gameplay

Players begin with 1,000 credits and can gradually expand their industrial infrastructure by constructing buildings, gathering resources and establishing production chains.
Available Commands:
Command                 -  Description
help                    -  Display available commands and building costs
build forest MyForest   -  Construct a forest with the name MyForest
tick                    -  Advance the simulation by one day
info                    -  Display simulation statistics
graph wood              -  Visualise historical wood quantities (wood can be replaced with other materials)
launch                  -  Execute an additional product-selling cycle
save game.csv           -  Save the current save file with the name game.csv
load game.csv           -  Load a save file with the name game.csv
cheat                   -  Obtain additional credits for experimentation
quit                    -  Exit the application

## Technical Difficulties

Planning out the architecture based on given structure
Input handling and parser creation
Engine design
Inheritance of classes

## Testing

Primary testing was done by hand by running the program many times and checking every individual feature against every kind of input. When primary testing was satisfied, program was ran through a tester given by
university.

## Future Improvements

Not intended

## Author

Maksym Maibroda
