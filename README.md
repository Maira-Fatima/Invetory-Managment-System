# 🍰 Bakery Inventory Management System

A Java-based desktop application for managing bakery product inventory, built as part of a Programming Fundamentals (PF) course project. The system provides both a **console-based (CLI)** interface and a **graphical (GUI)** interface for complete inventory control.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Data Format](#data-format)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Stock Thresholds](#stock-thresholds)
- [Technologies Used](#technologies-used)

---

## Overview

This system is designed for a bakery to manage its product inventory across four categories: **Cakes**, **Breads**, **Pastries**, and **Snacks**. Data is persisted in plain-text `.txt` files, allowing lightweight and portable storage without a database.

The project ships with two runnable entry points:
- **`Products.java`** — Terminal/console-driven menu system.
- **`InventoryGUI.java`** — Swing-based graphical interface for a more user-friendly experience.

---

## ✨ Features

### 📦 Product Management
- **View All Products** — Display the full inventory across all categories in a formatted table.
- **View by Category** — Filter products by Cakes, Breads, Pastries, or Snacks.
- **View Specific Fields** — Choose to display only Quantity, Sales Price, or Cost alongside Product ID and Name.

### ✏️ Modify Inventory
- **Add Product** — Add a new item to any category with an auto-generated unique Product ID.
- **Update Product** — Edit quantity, sales price, and cost of an existing product by its ID.
- **Delete Product** — Remove a product from inventory with confirmation prompt (CLI).

### 📊 Stock Level Monitoring
- **Low Stock Check** — Lists all items with quantity below the threshold (< 10 units).
- **High Stock Check** — Lists all items with quantity above the threshold (> 50 units).
- **Stock Alerts** — Combined view showing both low and high stock items with status labels.

### 🖥️ Dual Interface
- **CLI (`Products.java`)** — Menu-driven terminal interface with full navigation and error handling.
- **GUI (`InventoryGUI.java`)** — Java Swing window with tabbed panels, dialogs, and styled buttons.

---

## 📁 Project Structure

```
PF Project/
│
├── Products.java          # Console-based (CLI) application entry point
├── InventoryGUI.java      # Graphical (GUI) application entry point
│
├── cakes.txt              # Product data — Cakes category
├── breads.txt             # Product data — Breads category
├── pastries.txt           # Product data — Pastries category
├── snacks.txt             # Product data — Snacks category
│
├── Commentedcode          # Development notes / commented-out code
├── tempCodeRunnerFile.java # IDE temp file (can be ignored)
│
└── resources/             # Resources directory (reserved for future assets)
```

---

## 📄 Data Format

Each `.txt` file stores products in CSV format, one product per line:

```
ProductID,ProductName,Quantity,SalesPrice,Cost
```

**Example (`cakes.txt`):**
```
CAK456,BLACK FOREST CAKE,100,1500,2500
CAK567,FRUIT CAKE,10,1800,1000
CAK678,CHEESECAKE,14,2000,1100
```

### Product ID Format
IDs are auto-generated using the first 3 letters of the category + a random 3-digit number.

| Category | ID Prefix | Example |
|----------|-----------|---------|
| Cakes    | `CAK`     | `CAK456` |
| Breads   | `BRE`     | `BRE123` |
| Pastries | `PAS`     | `PAS789` |
| Snacks   | `SNA`     | `SNA321` |

---

## ▶️ How to Run

### Prerequisites
- **Java JDK 8+** installed
- A terminal or IDE (e.g., VS Code, IntelliJ IDEA, Eclipse)

### Compile

```bash
# Navigate to the project folder
cd "PF Project"

# Compile both source files
javac Products.java InventoryGUI.java
```

### Run — Console Interface

```bash
java Products
```

### Run — GUI Interface

```bash
java InventoryGUI
```

> ⚠️ **Important:** Run the application from the same directory as the `.txt` files, or the data files will not be found.

---

## 🧭 Usage Guide

### Console Interface (Products.java)

```
Navigating to Product Page...
1. View
2. Modify
3. Stock level check
4. Exit
Enter your choice: _
```

Navigate through the menu by entering the number corresponding to your choice. Sub-menus provide further options for each action.

### GUI Interface (InventoryGUI.java)

The main window presents four color-coded buttons:

| Button | Color | Action |
|--------|-------|--------|
| **View Products** | Steel Blue | Browse all/filtered inventory in tabbed tables |
| **Modify Products** | Medium Sea Green | Add, update, or delete products via dialogs |
| **Stock Level Check** | Goldenrod | View low stock, high stock, and all alerts |
| **Exit** | Indian Red | Close the application |

---

## 📉 Stock Thresholds

| Status | Condition |
|--------|-----------|
| 🔴 **Low Stock** | Quantity `< 10` units |
| 🟢 **Normal** | `10 ≤ Quantity ≤ 50` units |
| 🟡 **High Stock** | Quantity `> 50` units |

These thresholds are defined as constants and can be changed in the source code:

```java
// In InventoryGUI.java
private static final int LOW_STOCK_THRESHOLD  = 10;
private static final int HIGH_STOCK_THRESHOLD = 50;

// In Products.java (checkStockLevel method)
int lowStockThreshold  = 10;
int highStockThreshold = 50;
```

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java SE** | Core programming language |
| **Java Swing** | GUI framework (`javax.swing`) |
| **Java AWT** | Layout & graphics (`java.awt`) |
| **BufferedReader / BufferedWriter** | File I/O for reading/writing `.txt` data |
| **Scanner** | Console input handling in CLI mode |
| **JTable / DefaultTableModel** | Data display in GUI tables |

---

## 👤 Author

**Bilal**
Programming Fundamentals (PF) — University Project
