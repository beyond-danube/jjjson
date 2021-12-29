# jjjson
Small tools to operate JSON, especially in API test automation.  
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
  
While will not if sort it before comaring:

```Java
var expected = JsonSorter.getSortedJson({ "f1" : "Just wanted to say", "f2" : "I love you" })
var actual = JsonSorter.getSortedJson({ "f2" : "I love you", "f1" : "Just wanted to say" })
Assert.assertEquals(expected, actual)
```
