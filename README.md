# RUPizza

A JavaFX pizza ordering app I built for my Software Methodology class at Rutgers. You pick a style (New York or Chicago), pick a flavor (Deluxe, BBQ Chicken, Meatzza, or build your own), choose a size and crust, and the app tracks your current order plus every order the "store" has taken.

It's a small project but it covers a chunk of object oriented design that's easy to wave at on a resume: abstract classes, polymorphism, the factory pattern, JavaFX with FXML, and JUnit tests.

## What it does

* Two pizza styles: New York and Chicago, each with their own crust options and pricing
* Four preset flavors plus a build your own option
* Three sizes (Small, Medium, Large) with size based pricing
* Customizable toppings (limit of 7) on build your own pizzas
* A running cart for the current order
* A separate "store orders" view that shows every placed order, with the ability to cancel or export them

## Tech

* Java 21
* JavaFX 21 with FXML for the UI
* Maven for the build
* JUnit 5 for tests

## How the code is laid out

```
RUPizza/
├── src/
│   ├── main/
│   │   ├── java/rupizza/
│   │   │   ├── Main.java                    # Application entry point
│   │   │   ├── MainController.java          # Main menu controller
│   │   │   ├── OrderViewController.java     # Current order view
│   │   │   ├── StoreOrdersController.java   # All store orders view
│   │   │   ├── Pizza.java                   # Abstract base class
│   │   │   ├── NYPizza.java                 # New York style factory
│   │   │   ├── ChicagoPizza.java            # Chicago style factory
│   │   │   ├── PizzaFactory.java            # Factory interface
│   │   │   ├── Deluxe.java                  # Deluxe pizza
│   │   │   ├── BBQChicken.java              # BBQ Chicken pizza
│   │   │   ├── Meatzza.java                 # Meatzza pizza
│   │   │   ├── BuildYourOwn.java            # Customizable pizza
│   │   │   ├── Size.java                    # Size enum
│   │   │   ├── Crust.java                   # Crust enum
│   │   │   ├── Topping.java                 # Topping enum
│   │   │   ├── Order.java                   # Single order
│   │   │   └── StoreOrders.java             # Collection of orders
│   │   └── resources/rupizza/
│   │       ├── main-view.fxml
│   │       ├── order-view.fxml
│   │       ├── storeorders-view.fxml
│   │       └── images/                      # Pizza preview images
│   └── test/
│       └── java/rupizza/
│           └── BuildYourOwnTest.java        # JUnit tests
├── pom.xml
└── README.md
```

## Design choices worth pointing out

**Factory pattern.** `PizzaFactory` is an interface with two implementations, `NYPizza` and `ChicagoPizza`. The controller doesn't care which style you picked, it just asks the factory for a Deluxe (or whatever) and gets back the right object. Adding a new style later would be one new factory class, not a sweep through the controllers.

**Polymorphism for pricing.** `Pizza` is abstract with a `price()` method. Each concrete pizza overrides it. The order view doesn't branch on type, it just iterates over pizzas and asks each one what it costs.

**Enums where they belong.** `Size`, `Crust`, and `Topping` are enums, which kills a whole class of bugs around string typos and makes the UI dropdowns trivial.

**Build your own with a topping cap.** The `BuildYourOwn` class enforces a maximum of 7 toppings. There's a JUnit test for the boundary.

## Running it

You need JDK 21 and Maven.

```bash
cd RUPizza
mvn clean install
mvn javafx:run
```

Or open the project in IntelliJ and run `Main` directly.

## Tests

```bash
mvn test
```

The current suite covers the topping cap on `BuildYourOwn` and a couple of related edge cases. There's room to add more.

## Things that could be improved

* Persist store orders to disk so they survive a restart
* CSS theme for the UI (it's plain JavaFX styling right now)
* More test coverage on the factory and order classes
* A delivery option and an order status field

## Author

Baavansh Reddy Gundlapalli, written for CS213 Software Methodology at Rutgers.
