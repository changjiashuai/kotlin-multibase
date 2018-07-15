# kotlin-multibase

[![](https://img.shields.io/badge/made%20by-Protocol%20Labs-blue.svg?style=flat-square)](http://ipn.io)
[![](https://img.shields.io/badge/project-multiformats-blue.svg?style=flat-square)](https://github.com/multiformats/multiformats)
[![](https://img.shields.io/badge/freenode-%23ipfs-blue.svg?style=flat-square)](https://webchat.freenode.net/?channels=%23ipfs)
[![](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

> Implementation of [multibase](https://github.com/multiformats/multibase) -self identifying base encodings- in Kotlin.


## Install

Add the relevant dependency to your project:

#### Maven

```maven
<dependency>
  <groupId>io.ipfs.multiformats</groupId>
  <artifactId>kotlin-multibase</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

#### Gradle

```gradle
compile 'io.ipfs.multiformats:kotlin-multibase:1.0.0'
```


## Usage


```kotlin
val input:String = "..."
val decode = MultiBase.decode(input)
val inputByteArray: ByteArray = ....
val encode = MultiBase.encode(MultiBase.Base.BASE32, inputByteArray)
```


## Maintainers

Captain: [@changjiashuai](https://github.com/changjiashuai).

## Contribute

Contributions welcome. Please check out [the issues](https://github.com/changjiashuai/kotlin-multibase/issues).

Check out our [contributing document](https://github.com/multiformats/multiformats/blob/master/contributing.md) for more information on how we work, and about contributing in general. Please be aware that all interactions related to multiformats are subject to the IPFS [Code of Conduct](https://github.com/ipfs/community/blob/master/code-of-conduct.md).

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

[MIT](LICENSE) © 2016 Protocol Labs Inc.
