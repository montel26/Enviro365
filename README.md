# Enviro365 -Montel Wood


# Authentication
Basic authentication is implemented with the following credentials:

Username: sa
Password: password
# Waste Category API Documentation

This repository contains an API for managing waste categories, disposal methods, and recycling tips. The API allows users to **Create**, **Read**, **Update**, and **Delete** waste categories and related data.

## API Endpoints

### 1. **Get All Waste Categories**

Retrieve a list of all waste categories in the system.

```bash
curl http://localhost:8080/api/waste-categories
```

### Get a Specific Waste Category
Retrieve a specific waste category by its ID,replace the 1 with the specific Id.

```bash
curl http://localhost:8080/api/waste-categories/1
```

### Create a New Waste Category
Add a new waste category to the system. The body must include the following data:

name: The name of the waste category.
description: A short description of the waste category.
disposalGuidelines: Guidelines for disposing of the items in this category.
recyclingTips: Tips for recycling the items in this category.

```bash
curl -X POST http://localhost:8080/api/waste-categories -H "Content-Type: application/json" -d "{\"name\": \"Electronics\", \"description\": \"Electronic devices and components\", \"disposalGuidelines\": \"Must be taken to certified e-waste facilities\", \"recyclingTips\": \"Remove batteries before recycling\"}"
```

### Update an Existing Waste Category

Update an existing waste category by its ID. The request body should include the updated details:

name: The updated name of the waste category.
description: The updated description of the waste category.
disposalGuidelines: The updated disposal guidelines.
recyclingTips: The updated recycling tips.

```bash
curl -X PUT http://localhost:8080/api/waste-categories/{id} -H "Content-Type: application/json" -d "{\"name\": \"Updated Electronics\", \"description\": \"Updated description\", \"disposalGuidelines\": \"Updated guidelines\", \"recyclingTips\": \"Updated tips\"}"
```

### Delete a Waste Category
Delete an existing waste category by its ID.
```bash
curl -X DELETE http://localhost:8080/api/waste-categories/{id}
```

# Recycling Tips
Retrieve all recycling tips.
```bash
curl http://localhost:8080/api/recycling-tips
```

# Get Recycling Tip by ID
Retrieve a specific recycling tip.

```bash
curl http://localhost:8080/api/recycling-tips/{id}
```

# Get Recycling Tips by Category
Retrieve all recycling tips for a specific waste category.

```bash
curl http://localhost:8080/api/recycling-tips/category/{categoryId}
```

# Create New Recycling Tip
Add a new recycling tip.

```bash
curl -X POST http://localhost:8080/api/recycling-tips -H "Content-Type: application/json" -d "{\"processName\": \"Electronic Device Recycling\", \"description\": \"Process for recycling electronic devices\", \"processingSteps\": \"1. Remove batteries 2. Sort components\", \"benefitsDescription\": \"Reduces e-waste in landfills\", \"resourceSavings\": \"Recovers precious metals\"}"

```

# Update Recycling Tip
Update an existing recycling tip.

```bash
curl -X PUT http://localhost:8080/api/recycling-tips/{id} -H "Content-Type: application/json" -d "{\"processName\": \"Updated Process\", \"description\": \"Updated description\", \"processingSteps\": \"Updated steps\", \"benefitsDescription\": \"Updated benefits\", \"resourceSavings\": \"Updated savings\"}"
```

# Delete Recycling Tip
Delete a recycling tip.

```bash
curl -X DELETE http://localhost:8080/api/recycling-tips/{id}
```
# Waste Disposal Methods

# Get All Disposal Methods
Retrieve all waste disposal methods.

```bash
curl http://localhost:8080/api/waste-disposal
```

# Get Disposal Method by ID
Retrieve a specific disposal method.

```bash
curl http://localhost:8080/api/waste-disposal/{id}
```

# Get Disposal Methods by Category
Retrieve all disposal methods for a specific category.

```bash
curl http://localhost:8080/api/waste-disposal/category/{categoryId}
```

# Create New Disposal Method
Add a new disposal method.

```bash
curl -X POST http://localhost:8080/api/waste-disposal -H "Content-Type: application/json" -d "{\"methodName\": \"Electronics Disposal\", \"description\": \"Safe disposal of electronic waste\", \"safetyPrecautions\": \"Wear protective gear\", \"environmentalImpact\": \"Prevents toxic leachate\"}"

``` 

# Update Disposal Method
Update an existing disposal method.

```bash
curl -X PUT http://localhost:8080/api/waste-disposal/{id} -H "Content-Type: application/json" -d "{\"methodName\": \"Updated Method\", \"description\": \"Updated description\", \"safetyPrecautions\": \"Updated safety measures\", \"environmentalImpact\": \"Updated impact\"}"
``` 

# Delete Disposal Method
Delete a disposal method.

```bash
curl -X DELETE http://localhost:8080/api/waste-disposal/{id}
``` 



# Database
The application uses an H2 in-memory database:

Console URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:wastedb
Username: sa
Password: password