# MelissaBatch

## Overview
The purpose of this Spring starter package is to provide a means by which you can reduce calls to Melissa in a high traffic application. It is designed to coalesce up to 100 requests into a single Melissa REST based batch.

The in-memory queue and workers have several settings that govern how they are periodically polled for records. The value `melissabatch.batch.timer` is the most important for response times as it essentially introduces a delay before processing requests (also referred to as "backpressure"). The delay will regulate how often the in-memory queue is drained, thus large delays would mean more records per batch if the traffic is high enough.

When the delay expires and processing begins the batching worker will greedily consume the in-memory queue. It is possible to reach enough traffic that the delay actually becomes a layering effect, calling yet more batches in parallel to prior triggered loops that are still processing. This will be governed by Spring's internal thread model for `@Scheduled(fixedRate = ?)` implementations.

MelissaBatch is written to depend on Spring's default thread model. If you need to limit the number of threads servicing MelissaBatch then you would specify a smaller `melissabatch.batch.cache` and then configure the `melissabatch.batch.cacheTimeOut` to keep the request from being rejected for batching.

## Deploying MelissaBatch
This starter package was designed to be used in a microservice with the protocols of your choice. A typical deployment would be to create either a REST or messaging based listener that converts your native Address request object to the specific request record for MelissaBatch. Then, you would add that request to the batch, receiving a `CompleteableFuture<MelissaAddress>` in return. The futures `.get()` or `.get(timeout)` will then return either the requested `MelissaAddress` or an error if the connection was not successful.

## Performance
This starter will eventually be instrumented for metrics. They were taken out for the moment to faciltate these early releases.


## Maven / Gradle Support
This project will be available via a repository soon. If you need it sooner please drop a note.


## Configuration
The configurations for the service that are not Melissa specific (batch and connection) all have defaults. All Melissa parameters are NOT defaulted.

1. **melissabatch.customerId** = This will be your Melissa issues Customer ID
1. **melissabatch.service.base** = https://personator.melissadata.net
1. **melissabatch.service.uri** = /v3/WEB/ContactVerify/doContactVerify
1. **melissabatch.options** = These would be the Melissa defined options you wish to enable
1. **melissabatch.actions** = The most common action would be Check but check with Melissa documentation
1. **melissabatch.columns** = These are again Melissa defined parameters (Columns)
1. **melissabatch.batch.timer** = This defines the period between batches
1. **melissabatch.batch.size** = This is defined by Melissa is current a maximum of "100"
1. **melissabatch.batch.cacheTimeOut** = This is the timeout that will govern the queue when it has reached capacity
1. **melissabatch.batch.cache** = This is the in-memory queue waiting for batching. It should be a multiple of melissabatch.batch.size
1. **melissabatch.connection.timeout** = This is the HTTP timeout
1. **melissabatch.connection.retry** = This is the number of times that the batches will be retried before accepting failure
1. **melissabatch.connection.retryTime** = This is the time between retries

## Important Documentation
+ MelissaData [Personator Consumer Docs](http://wiki.melissadata.com/index.php?title=Personator_Consumer)
+ MelissaData [Personator Consumer JSON](http://wiki.melissadata.com/index.php?title=Personator_Consumer%3AJSON)
+ MelissaData [Personator Consumer  Result Codes](http://wiki.melissadata.com/index.php?title=Result_Code_Details#Personator_Consumer)