
# Tenpo Challenge

This is a challenge created by Tenpo to test my skills as a Backend Software Engineer


## API Reference

#### Get arithmetic calculation

```http
  GET /api/arithmetic
```

| Parameter      | Type    | Description                                    |
| :--------      | :------ | :--------------------------------------------- |
| `first_value`  | `float` | **Required**. first value for the calculation  |
| `second_value` | `float` | **Required**. second value for the calculation |

#### Get all endpoints called history (audit)

```http
  GET /api/audit
```

| Parameter   | Type     | Description                                   |
| :--------   | :------- | :-------------------------------------------- |
| `page`      | `int`    | **Optional**. page number to retrieve         |
| `size`      | `int`    | **Optional**. number of endpoints to retrieve |

## Run Locally

Clone the project

```bash
  git clone git@github.com:nicolas-alvarez12/tenpo-challenge.git
```

Go to the project directory

```bash
  cd tenpo-challenge
```

run docker compose to install and run both apps, postgres and redis (be sure to have docker installed locally first)

```bash
  docker-compose up -d 
```

run postman (install it if needed first) and import the collection located in postman/tenpo.postman_collection.json to test the endpoints

## Authors

- [@niconalvarez](https://github.com/niconalvarez)

