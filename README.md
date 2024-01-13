# Splitzee

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
- [Build and Deployment](#build-and-deployment)
- [Lines of Code](#Total-Lines-of-Code)
- [Features](#features-Delivered)
- [Functionalities](#Functionalities-implemented)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Node.js installed on your machine.
- npm (Node Package Manager) or yarn installed.
- Any IDE (Intellij Recommended)

## Getting Started

To get a local copy up and running, follow these simple steps.

### Installation
#### Frontend Installation

1. Navigate to the project directory: 

    ```cd Tripsplit/Frontend```
2. Install Dependencies

    ``` npm install ```

#### Backend Installation

1. Navigate to the project directory: 

    ```cd Tripsplit/Backend```
2. Install Dependencies

    ``` mvn install ```

### Build and Deployment

#### Frontend

In the project directory, you can run the following scripts to build and deploy the app:

- Start development server:

    ```npm start```

  Open http://localhost:3000 to view it in the browser.
- Build for production:

    ```npm run build```

    Builds the app for production to the build folder.

#### Backend

Open the project in Intellij or any other ide

- Configure java 17 as application SDK
- Build the project and run ``com.tripsplit.TripSplitApplication``

## Total Lines of Code

### Backend : 857 LOC
![Alt text](./Splitzee/Frontend/image.png)

### Frontend : 942 LOC
![Alt text](./Splitzee/Frontend/image-1.png)

## Features Delivered

1.	Adding and splitting expenses evenly or based on custom ratios
2.	Creating groups amongst different users
3.	Tracking balances owed on the dashboard
4.	Settling up expenses when users reimburse each other
5.  Monitoring application performance using prometheus and grafana

## Functionalities Implemented

1.	Users can register/login with email and password
2.	Users can add expenses by entering details like amount, category, date, etc.
3.	Users can add contacts/groups to share expenses with
4.	Users can split an expense evenly or unevenly with contacts
5.	Users can view dashboard showing who owes who and settle balances

