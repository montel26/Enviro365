# Enviro365 -Montel Wood

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