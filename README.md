# Charm Bar Store - Custom Accessory Designer

A complete Java Object-Oriented Programming (OOP) project that simulates a charm bar store where customers can design custom accessories (bracelets or necklaces) by selecting and combining various charms.

## Overview

This application demonstrates all essential OOP concepts through a real-world charm bar store scenario:
- **Modularity**: Separate packages for model, logic, and GUI
- **Hierarchy**: Class inheritance trees for charms and accessory bases
- **Composition**: Accessories composed of bases and charms
- **Reuse**: Shared methods and common functionality
- **Encapsulation**: Private fields with getters/setters
- **Subtyping**: Interfaces and abstract classes
- **Information Hiding**: Protected/private access modifiers
- **Abstraction**: Focus on essential characteristics
- **Inheritance**: Extends relationships throughout
- **Polymorphism**: Dynamic method overriding and behavior
- **Exception Handling**: Custom exceptions for error scenarios

## Features

### Charm Types
- **Heart Charms**: Can include custom engraving
- **Star Charms**: Available in 5-point or 6-point styles, optional gemstone center
- **Animal Charms**: Cat, Dog, Butterfly, Dolphin with optional colored enamel

### Accessory Bases
- **Bracelets**: Hold up to 8 charms
- **Necklaces**: Hold up to 12 charms

### Materials
- Silver (standard pricing)
- Gold (premium pricing)

### Customization Options
- Multiple finish styles: Polished, Matte, Antique
- Engraving for heart charms
- Gemstone additions for star charms
- Enamel details for animal charms

## Project Structure

```
charm-bar-store/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── charmstore/
│   │               ├── model/          # Data model classes
│   │               │   ├── Charm.java (abstract)
│   │               │   ├── HeartCharm.java
│   │               │   ├── StarCharm.java
│   │               │   ├── AnimalCharm.java
│   │               │   ├── AccessoryBase.java (abstract)
│   │               │   ├── Bracelet.java
│   │               │   ├── Necklace.java
│   │               │   ├── Accessory.java
│   │               │   ├── Priceable.java (interface)
│   │               │   └── Customizable.java (interface)
│   │               ├── logic/          # Business logic
│   │               │   ├── CharmBarLogic.java
│   │               │   ├── CharmStoreException.java
│   │               │   └── InvalidSelectionException.java
│   │               └── gui/            # User interface
│   │                   └── CharmStoreApp.java
│   └── test/
│       └── java/
│           └── com/
│               └── charmstore/
│                   └── CharmStoreTest.java
├── pom.xml                             # Maven configuration
└── README.md
```

## OOP Concepts Demonstrated

### 1. Abstraction
- Abstract classes: `Charm`, `AccessoryBase`
- Abstract methods: `getDescription()`, `getType()`
- Interfaces: `Priceable`, `Customizable`

### 2. Inheritance
- `HeartCharm`, `StarCharm`, `AnimalCharm` extend `Charm`
- `Bracelet`, `Necklace` extend `AccessoryBase`
- Exception hierarchy: `InvalidSelectionException` extends `CharmStoreException`

### 3. Polymorphism
- Method overriding: `calculatePrice()`, `getDescription()`
- Dynamic binding: Different charm types calculate prices differently
- Interface implementation: Multiple classes implement `Priceable`

### 4. Encapsulation
- Private fields with public getters/setters
- Protected fields accessible only to subclasses
- Controlled access to internal state

### 5. Composition
- `Accessory` HAS-A `AccessoryBase`
- `Accessory` HAS-A list of `Charm` objects
- Building complex objects from simpler ones

### 6. Exception Handling
- Custom exceptions: `CharmStoreException`, `InvalidSelectionException`
- Try-catch blocks throughout GUI and logic layers
- Proper error reporting and validation

## Building and Running

### Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- NetBeans IDE (optional, but recommended)

### Build with Maven

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn package
```

### Run the Application

```bash
# Run directly with Maven
mvn exec:java -Dexec.mainClass="com.charmstore.gui.CharmStoreApp"

# Or run the packaged JAR
java -jar target/charm-bar-store-1.0-SNAPSHOT.jar
```

### Open in NetBeans
1. Open NetBeans IDE
2. File → Open Project
3. Navigate to the charm-bar-store directory
4. Select and open the project
5. Right-click project → Run

## Usage Guide

### Creating an Accessory

1. **Launch the application**
2. **Click "Create New Accessory"**
3. **Enter customer name** (minimum 2 characters)
4. **Select accessory type**: Bracelet or Necklace
5. **Choose material**: Silver or Gold

### Adding Charms

1. **Click "Add Charm"** (only enabled after creating an accessory)
2. **Select charm type**: Heart, Star, or Animal
3. **Choose material**: Silver or Gold
4. **If Animal charm**: Select animal type (Cat, Dog, Butterfly, Dolphin)
5. **Customize the charm**:
   - Heart: Optional engraving
   - Star: Optional gemstone, select points (5 or 6)
   - Animal: Optional colored enamel
6. **Select finish**: Polished, Matte, or Antique

### Completing an Order

1. **Review your accessory** with "View Current Accessory"
2. **Click "Complete Order"** to finalize
3. **Order is saved** to history with timestamp

### Saving to File

1. **Click "Save to File"**
2. **Enter filename** (extension added automatically)
3. **File saved** with complete accessory details

## Testing

The project includes comprehensive unit tests covering:
- Charm creation (polymorphism)
- Accessory base creation
- Composition and assembly
- Price calculation
- Exception handling
- Validation logic

Run tests with:
```bash
mvn test
```

## Technical Details

### Design Patterns
- **Factory Pattern**: `CharmBarLogic` creates objects based on type
- **Composition Pattern**: `Accessory` composed of base and charms
- **MVC-like Pattern**: Separation of model, logic, and GUI

### Exception Handling
- `InvalidSelectionException`: Thrown for invalid user selections
- `CharmStoreException`: Base exception for store operations
- File I/O exceptions properly handled and reported

### Price Calculation (Polymorphic)
- Each charm type calculates its own price
- Base prices vary by material (gold vs silver)
- Customizations add to base price
- Total accessory price is sum of all components

## Example Scenarios

### Scenario 1: Simple Bracelet
```
Customer: John Doe
Base: Silver Bracelet (7.5", $20.00)
Charms: 
  - Silver Heart Charm with "Love" engraving ($15.00)
  - Silver Star Charm with gemstone ($20.00)
Total: $55.00
```

### Scenario 2: Elaborate Necklace
```
Customer: Jane Smith
Base: Gold Necklace (18", $50.00)
Charms:
  - Gold Heart Charm, matte finish ($17.00)
  - Gold Butterfly Charm with enamel ($22.00)
  - Gold Star Charm, 6-point, gemstone ($26.00)
Total: $115.00
```

## Future Enhancements
- Database persistence for orders
- Customer account management
- Inventory tracking
- Online ordering capabilities
- 3D preview of accessories

## License
This project is for educational purposes demonstrating OOP concepts in Java.

## Author
OOP Java Project - Charm Bar Store Application

