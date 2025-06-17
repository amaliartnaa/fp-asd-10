# Logistics & Package Delivery Information System

This is a **Java** application that simulates a comprehensive logistics and package delivery information system. It's designed to efficiently manage location data, calculate optimal delivery routes, and determine appropriate delivery services based on various package characteristics.

---

## Key Features

* **Location Management**: Organizes and stores city, province, district, sub-district, and postal code data using a **Binary Search Tree (BST)**. This ensures efficient searching and structured storage of location information.
* **Distance Calculation**: Utilizes **Dijkstra's algorithm** within a **Graph** implementation to find the shortest distances between an origin and destination city. This guarantees optimal and efficient delivery routes.
* **Delivery Service Selection**: Dynamically determines the most suitable delivery service (e.g., **Cargo**, **Regular**, **Express**) based on the package's weight and specific attributes, such as whether it contains liquids or batteries. This includes robust package validation.
* **Estimated Delivery Time**: Provides an accurate time estimate for package delivery, taking into account the calculated distance and the chosen delivery service.
* **Summary Export**: Allows users to export a detailed summary of delivery information to a `.txt` file for record-keeping and easy access.

---

## Technical Highlights

This project showcases a solid understanding of fundamental computer science concepts and **object-oriented programming (OOP)** principles in Java, including:

* **Data Structures**: Practical application of **Graphs** for representing interconnected cities and **Binary Search Trees (BSTs)** for hierarchical location data.
* **Algorithms**: Implementation of **Dijkstra's algorithm** for shortest path finding, a cornerstone in route optimization.
