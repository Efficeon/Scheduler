##1.​ Create job - POST /jobs
http://localhost:9081/jobs/

##2. Get job status - GET /jobs/${job_id}
http://localhost:9081/jobs/${job_id}

##3. Recall job - DELETE /jobs/${job_id}
http://localhost:9081/jobs/${job_id}

## Database configuration
Data configuration in application.properties 



## Swagger REST API documentation presented here:
http://localhost:9081/swagger-ui.html

## Architecture
Hylaa Scheduler Service is a component of the PaaS application. It allows PaaS and other SaaS services to schedule one-time or recursive tasks via its RESTful API. 
The Scheduler Service consists of following modules: 
1. HTTP Server: the HTTP server is based on Spring Boot and is able to handle RESTful API request from other services.
2. Scheduler: the scheduler is based on Quartz, it schedules and keeps tracks of requested tasks.
3. Adapters: the adapters are the components that execute the tasks. They define their own parameters for the different tasks. For the current phase only HTTP task is required.
4. Common interfaces and classes: This module defines the common interfaces implemented by Adapter classes and customized Exceptions, etc.



3.​ Workflow (--no information--)

​4.​ Data model (--no information--

​4.1.​ Data structure (--no information--)

​4.2.​ Database design (--no information--)

​5.​ System design description 
​5.1.​ API
​5.1.1.​ Create job - POST /jobs
Request:
```
{
 "body": {
   "task": { // the format of a task is defined by each adapter
     "method": "POST", // GET|POST|PUT|DELTE
     "url": "http://example.com/", //support both "http" and "https"
     "headers": { // optional
       "Content-Type": "application/json", // optional, default to be "application/json"
       "Accept": "application/json", // optional, default to be "application/json"
       "Authorization": "Bearer XXXXXX"
     },
     "data": ... // request body, optional, not required for GET, can be a json object or a string
   },
   "type": "http", // optional, default to be "http"
   "scheduled_at": "*/10 * * * 1-5", // Ex: Monday to Friday, every 10 minutes
   "execute_times": 10, // optional, times of execution
   "start_time": "2017-02-01 12:00:00", // optional , default to be now
   "end_time": "2018-02-01 12:00:00", // optional, default to run forever
   "time_zone": "Asia/Singapore", // optional , default "Asia/Singapore"
   "callback_url": "https://result.com/callback" // optional
 }
},
```

Response:
```
{
 "body": {
   "job_id": "12345678" // unique id of the job
 }
}
```

5.1.2.​ Get job status - GET /jobs/${job_id}
Request:
```
{
 "body": {
   "job_id": "12345678" 
 }
}
```

Response:
```
{
 "body":{
   "job_id": "12345678",
   "task": {
     "method": "POST",
     "url": "http://example.com/",
     "headers": {
       "Content-Type": "application/json",
       "Accept": "application/json",
       "Authorization": "Bearer XXXXXX"
     },
     "data": {}
   },
   "type": "http",
   "scheduled_at": "*/10 * * * 1-5", // Ex: Monday to Friday, every 10 minutes
   "execute_times": 10,
   "start_time": "2017-02-01 12:00:00",
   "end_time": "2018-02-01 12:00:00",
   "time_zone": "Asia/Singapore",
   "callback_url": "https://result.com/callback",
   "next_run_at": "2017-02-01 12:20:00",
   "last_run_at": "2017-02-01 12:10:00",
   "last_run_result": {
     "code": 200,
     "body": "..."
   }
 }
}
```

5.1.3.​ Recall job - DELETE /jobs/${job_id}
Request:
```
{
 "body": {
   "job_id": "12345678" 
 }
}
```
Response:
```
{
 "code": 200
 "message": "job deleted successfully",
}

5.1.4. Callback - for other SaaS to implement
POST https://result.com/callback
{
 "body": {
   "job_id": "12345678",
   "result": {
     "code": 200,
     "body": "..."
   }
 }
}
```




