# MelissaBatch
A 'Spring Starter' for in-memory batching addresses and person data to Melissa Personator.

## Configuration

1. **melissabatch.customerId** = This will be your Melissa issues Customer ID.s
1. **melissabatch.service.base** = https://personator.melissadata.net
1. **melissabatch.service.uri** = /v3/WEB/ContactVerify/doContactVerify
1. **melissabatch.options** = These would be the Melissa defined options you wish to enable.
1. **melissabatch.actions** = The most common action would be Check but check with Melissa documentation.
1. **melissabatch.columns** = These are again Melissa defined parameters (Columns).
1. **melissabatch.batch.timer** = This defines the period between batches.
1. **melissabatch.batch.size** = This is defined by Melissa is current a maximum of "100."
1. **melissabatch.batch.cacheTimeOut** = This is the timeout that will govern the queue when it has reached capacity.
1. **melissabatch.batch.cache** = This is the in-memory queue waiting for batching. It should be a multple of melissabatch.batch.size
1. **melissabatch.connection.timeout** = This is the HTTP timeout.
1. **melissabatch.connection.retry** = This is the number of times that the batches will be retired before accepting failure
1. **melissabatch.connection.retryTime** = This is the time between retries.

## Important Documentation

+ MelissaData [Personator Consumer Docs](http://wiki.melissadata.com/index.php?title=Personator_Consumer)
+ MelissaData [Personator Consumer JSON](http://wiki.melissadata.com/index.php?title=Personator_Consumer%3AJSON)
+ MelissaData [Personator Consumer  Result Codes](http://wiki.melissadata.com/index.php?title=Result_Code_Details#Personator_Consumer)