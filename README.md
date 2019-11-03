# elasticsearch-analysis-jikyo-romaji

`elasticsearch-analysis-jikyo-romaji` is a token filter to romanize Japanese hiragana/katakana string by standard and IME typing style.
[See more about Romaji.](https://github.com/jikyo/romaji4j)
Note that this token filter `jikyo_romaji` assumes to work with `tokenizer: keywrod`.


# Supported Elasticsearch versions

* [7.3.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.3.1)
* [7.3.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.3.0)
* [7.2.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.2.1)
* [7.2.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.2.0)
* [7.1.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.1.1)
* [7.1.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.1.0)


# Installation

```bash
$ sudo bin/elasticsearch-plugin install https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/download/v7.3.1/analysis-jikyo-romaji-7.3.1.zip
```


# Usage

### settings sample

```JSON

            "your_analyzer": {
                "type": "custom",
                "tokenizer": "keyword",
                "char_filter": [
                    "icu_normalizer"
                ],
                "filter": [
                    "jikyo_romaji"
                ]
            },
```

### _analyze sample

```bash
$ curl -H "Content-Type: application/json" -XGET "localhost:9200/_analyze?pretty" -d '
{
  "tokenizer" : "keyword",
  "filter" : ["jikyo_romaji"],
  "text" : "あっち"
}'
{
  "tokens" : [
    {
      "token" : "あっち",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "acchi",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "altsuchi",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "altuchi",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "altuti",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "atti",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "axtuti",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "word",
      "position" : 0
    }
  ]
}
```

# Build

```bash
# Verification task
$ gradle check -Dtests.security.manager=false
# Build task
$ gradle assemble
```
