# elasticsearch-analysis-jikyo-romaji

`elasticsearch-analysis-jikyo-romaji` is a token filter to romanize Japanese hiragana/katakana string by standard and IME typing style.
[See more about Romaji.](https://github.com/jikyo/romaji4j)
Note that this token filter `jikyo_romaji` assumes to work with `tokenizer: keywrod`.

# Build information

- JDK `17` is used with Elasticsearch 8.1.2

# Supported Elasticsearch versions

* 7.8: [7.8.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.8.0)
* 7.7: [7.7.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.7.1), [7.7.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.7.0)
* 7.6: [7.6.2](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.6.2), [7.6.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.6.1), [7.6.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.6.0)
* 7.5: [7.5.2](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.5.2), [7.5.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.5.1), ~~7.5.0~~
* 7.4: [7.4.2](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.4.2), [7.4.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.4.1), [7.4.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.4.0)
* 7.3: [7.3.2](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.3.2), [7.3.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.3.1), [7.3.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.3.0)
* 7.2: [7.2.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.2.1), [7.2.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.2.0)
* 7.1: [7.1.1](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.1.1), [7.1.0](https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/tag/v7.1.0)


# Installation

```bash
$ sudo bin/elasticsearch-plugin install https://github.com/jikyo/elasticsearch-analysis-jikyo-romaji/releases/download/v7.8.0/analysis-jikyo-romaji-7.8.0.zip
```


# Usage

### settings sample

```
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
# see build/distributions/
```
