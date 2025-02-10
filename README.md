# GasMonitor

&#x20;&#x20;

GasMonitor is a real-time gas monitoring system using WebSockets and Spring Boot.

## Features

✅ Real-time gas level monitoring\
✅ WebSocket-based communication\
✅ Data storage and analysis\
✅ Dashboard for visualization\
✅ Alert system for threshold breaches

## Technologies Used

| Technology                | Description                     |
| ------------------------- | ------------------------------- |
| **Java 17+**              | Backend framework (Spring Boot) |
| **WebSockets**            | Real-time data streaming        |
| **MongoDB**               | NoSQL data storage              |
| **Javascript and HTML**   | Frontend visualization          |

## Installation

### Prerequisites

Ensure you have the following installed:

- Java 17+
- Docker & Docker Compose

### Steps

1. Clone the repository:
   ```sh
   git clone https://github.com/acbueno/gasmonitor.git
   cd gasmonitor
   ```
2. Start the necessary services using Docker Compose:
   ```sh
   docker-compose up -d
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Configuration

Modify the `application.properties` file to set up WebSocket configurations and database settings.

## Usage

- Access the **dashboard** at `http://localhost:8080`
- Monitor gas levels **in real time via WebSockets**

## WebSocket API Example

Connect to the WebSocket:

```javascript
const socket = new WebSocket("ws://localhost:8080/gas-monitor");

socket.onmessage = (event) => {
    console.log("Gas level update:", event.data);
};
```

## Contributing

Contributions are welcome! Feel free to submit issues and pull requests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please contact [Anderson Bueno](mailto\:anderson.carlosb@gmail.com).

---

### Screenshot




