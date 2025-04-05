deploy-lambda:
	./gradlew build
	cd infrastructure && cdk deploy

destroy-lambda:
	./gradlew build
	cd infrastructure && cdk destroy

test-lambda:
	ab -n 1000 -c 50 https://6lfvd4ggg1.execute-api.us-east-2.amazonaws.com/api/hello

test-lambda-snapstart:
	ab -n 1000 -c 50  https://6lfvd4ggg1.execute-api.us-east-2.amazonaws.com/api/hello2
