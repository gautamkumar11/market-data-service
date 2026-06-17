# Market Data Service

## Overview
This project is a Market Data Service that acts as a proxy between OKX public Market Data APIs and browser clients. Clients never communicate directly with OKX. The backend fetches market data from OKX and streams it to connected clients.

---

## Features

### Authentication
- Simple login using a hardcoded username and password store.
- User session management with single active session enforcement.

### Market Overview
- Fetches the top 20 spot trading pairs by 24-hour trading volume.
- Displays:
  - Symbol
  - Last traded price
  - 24-hour price change percentage
  - 24-hour volume
- Data automatically refreshes at a configured interval.

 
## Technology Stack

### Backend
- Java 17
- Spring Boot 4
- Spring WebSocket
- WebClient / OpenFeign
- OKX REST API and WebSocket APIs

