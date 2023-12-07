set -e
./gradlew :cu:clean cu:build -x cu:test && ./gradlew cu:publishToMavenLocal
./gradlew eu:clean eu:jar -x eu:test && ./gradlew eu:publishToMavenLocal
./gradlew :rep-mongo:clean :rep-mongo:build -x test && ./gradlew :rep-mongo:publishToMavenLocal
./gradlew :rep-mysql:clean :rep-mysql:build -x test && ./gradlew :rep-mysql:publishToMavenLocal


