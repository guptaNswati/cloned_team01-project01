# Planet Hopper
##### team01-project01
Project folder: PlanetHopper/

Brief description of submitted files:

###### Celestial

src/celestial/Celestial.java
   - An object of class Celestial has 4 members, coordinate, color, name, radius.
   - It has a default and a 4 parameter constructor, and getters and setters.
   - It has a draw method to draw the celestial body on GUI.

src/celestial/Planet.java
   - An object of a Planet class inherits from Celestial.
   - Three additional members, distanceToSun, periodInMS, angleToSun.

src/celestial/Starfield.java
   - Creates stars in the game background.
   
---

###### Information

src/information/Information.java
   - Process and store celestial bodies' information.

src/information/SidePanel.java
   - GUI side panel.
   
---

###### Main

src/main/Main.java
   - Main class that initializes game panes.
   
---

###### Menu

src/menu/MainMenu.java
   - Button version of the menu.

src/menu/Menu.java
   - Overlay version of the menu.
   
---

###### Physics

src/physics/Constants.java
   - Contains constants for use in calculations.

src/physics/Physics.java
   - Methods to calculate planet coordinates, and ship coordinates, momentum, and collisions.
   
---

###### Ship

src/ship/Arrow.java
   - Arrow to indicate ship angle/thrust.

src/ship/Ship.java
   - The ship class contains data used for physics calculations on itself, and methods to manipulate that data.
   
---

###### Update

src/update/GameObjectives.java
   - Store data on and control game objectives.

src/update/Target.java
   - Draws a target marker indicating the objective.

src/update/Update.java
   - An update object contains all dynamic graphical elements.
   - It contains a paintComponent method necessary for GUI.
   - The run method updates physical coordinates and GUI elements.

---

###### Resources

resources/planets
   - Pictures of all planets and the sun.

resources/info.xml
   - Data about each planet displayed in-game.

resources/Run.jpg
   - Running screenshot.

![Running screenshot](resources/Run.png?token=AOcnylhWOPnpVm-f-vvbURAQQdPoII2Zks5XQdl2wA%3D%3D)

resources/Screen Recording.mp4
   - Video recording of gameplay.

resources/Target.png
   - Image for target marker indicating objective.

resources/UML Diagram.png
   - UML Diagram of significant classes in Planet Hopper.

![UML Diagram](resources/UML%20Diagram.png?token=AOcnyknBODC1WY1AVeUr4kD-F2POimQJks5XQdmgwA%3D%3D)

---

###### Image

image/PlanetHopper.jpg
   - Planet Hopper mock up.

![Mock up](image/PlanetHopper.jpg?token=AOcnyskIh0wSB8YIORAVtlnr8rbDvaKnks5XQdj-wA%3D%3D)

---
   
Project1Proposal.md
   - Project proposal of Planet Hopper.

README.md
   - Overview of all submitted files.
