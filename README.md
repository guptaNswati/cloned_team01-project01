# team01- project01
Project folder:
PlanetHopper/


Brief description of submitted files:

src/celestial/Celestial.java
   - An object of class Celestial has 4 members, coordinate, color, name, radius.
   - It has a default and a 4 parameter constructor, and getters and setters.
   - It has also a draw method to draw the celestial on GUI.

src/celestial/Planet.java
   - An object of a Planet class inherits from Celestial.
   - Three additional members, distanceToSun, periodInMS, angleToSun.

src/celestial/Starfield.java
   - Creates stars in the universe in the GUI background.

src/information/Information.java
   - Process planets information file.

src/information/SidePanel.java
   - The side panel of the GUI.

src/main/Main.java
   - Main class that initializes game panes.

src/menu/MainMenu.java
   - Button version of menu.

src/menu/Menu.java
   - In game version of the menu.

src/physics/Constants.java
   - A class that contains constants that will be used in calculations.

src/physics/Physics.java
   - Methods to calculate planet coordinates,
   - and ship coordinates, momentum, and collisions.

src/ship/Arrow.java
   - An arrow to draw on GUI. Also contains and inner private class for key
   - control.

src/ship/Ship.java
   - The ship class contains data used for physics calculations on itself,
   - and methods to manipulate that data.

src/update/GameObjectives.java
   - Controls game's objectives.

src/update/Target.java
   - The target icon object.

src/update/Update.java
   - An update object contains all dynamic graphical elements.
   - It contains a paintComponent method necessary for GUI.
   - The run method updates physical coordinates and GUI elements.


   ==============================================================================

resources/planets
   - Pictures of all planets and the sun

resources/info.xml
   - Data about each planet that's displayed in game.

resources/Run.jpg
   - Screenshot of opening screen

resources/Screen\ Recording.mp4
   - Video recording of gameplay

resources/Target.png
   - Picture of target markers that circle target planet (for landing).

resources/UML\ Diagram.png
   - UML Diagram of significant classes of Planet Hopper.


   ==============================================================================

image/PlanetHopper.jpg
   - Picture of Planet Hopper prototype.


   ==============================================================================
   
Project1Proposal.md
   - Project proposal of Planet Hopper


   ==============================================================================

README.md
   - Overview of all submitted files.
