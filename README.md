# jjjson
Small tools to operate JSON, especially in API test automation.  
  
[![Run Tests](https://github.com/beyond-danube/jjjson/actions/workflows/maven.yml/badge.svg)](https://github.com/beyond-danube/jjjson/actions/workflows/maven.yml)
## Sorter
Used to compare JSONs by content, when it should be same, while order might be diffrent.
```JSON
{
  "f1" : "Just wanted to say",
  "f2" : "I love you"
}

{
  "f2" : "I love you",
  "f1" : "Just wanted to say"
}
```

So this will result false positive failure:
```Java
var expected = { "f1" : "Just wanted to say", "f2" : "I love you" }
var actual = { "f2" : "I love you", "f1" : "Just wanted to say" }
Assert.assertEquals(expected, actual)
```
  
While will not if sort it before comparing:

```Java
var expected = JsonSorter.getSortedJson({ "f1" : "Just wanted to say", "f2" : "I love you" })
var actual = JsonSorter.getSortedJson({ "f2" : "I love you", "f1" : "Just wanted to say" })
Assert.assertEquals(expected, actual)
```
## Usage
Library is being published to Maven Central Repository, so usage is same as any other library.
### Maven
```xml
<dependencies>
    <dependency>
        <groupId>io.siniavtsev</groupId>
        <artifactId>jjjson</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle
```gradle
dependencies {
    implementation 'io.siniavtsev:jjjson:1.0.0'
}
```

