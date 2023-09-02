# Backend for spotmap, a React Native app
REST api to handle authentication and database

Frontend: https://github.com/Lauri-Iivarinen/spotmap

# How to run
Don't

# Endpoints

<Details>
    <Summary>
        Health check / test
    </Summary>

## *GET*  -  /api/test

return  : **string** -> "test"

## *GET*  -  /api/test2

return  : **string** -> "test2"

</Details>


<Details>
    <Summary>
        Authentication
    </Summary>

## *GET*  -  /api/token

**HEADER**  -  Basic auth

return  :  **string**

</Details>

<Details>
    <Summary>
        User
    </Summary>

## *POST*  -  /newuser

**BODY**  -  

    {
      username: string
      passwordHash: string
    }

return  :  string

## *GET*  -  /api/user

**HEADER**  -  Bearer Token

return  :  JSON  

    {
      id: Long
      userId: Long
      username: string
      role: string
      spots: List
      likes: List
      dislikes :  List
    }
</Details>

<Details>
    <Summary>
        Spot
    </Summary>

## *GET*  -  /api/spots/{id}

**HEADER**  -  Bearer Token

return  :  JSON 

    {
      id: Long
      name: string
      image: string
      description: string
      lon: double
      lat: double
      likes: int
      dislikes: int
      user: User
      comments: List
    }

## *GET*  -  /api/spots

**HEADER**  -  Bearer Token

return : List<*Spot*> (see above)

## *POST*  -  api/spots

**HEADER**  -  Bearer Token

**BODY**

    {
      name: string
      image: string
      description: string
      lon: double
      lat: double
    }

return  :  JSON 

    {
      id: Long
      name: string
      image: string
      description: string
      lon: double
      lat: double
      likes: int
      dislikes: int
      user: User
      comments: List
    }

##  *POST*  -  api/spots/like/{id}

**HEADER**  -  Bearer Token

return : Spot

##  *POST*  -  api/spots/dislike/{id}

**HEADER**  -  Bearer Token

return : Spot

##  *GET*  -  api/spots/delete/{id}

**HEADER**  -  Bearer Token

return : string

</Details>

<Details>
    <Summary>
        Comments
    </Summary>

##  *GET*  -  api/comments

**HEADER**  -  Bearer Token

return  :  List<*Comment*>

    Comment:
    {
      comment: string
      user: {
        userId: Long
      }
      spot: {
        id: Long
      }
    }

##  *POST*  -  api/comments

**HEADER**  -  Bearer Token

**BODY**

    {
      comment: string
      user: {
        userId: Long
      }
      spot: {
        id: Long
      }
    }
    
return  : string

</Details>

# Database

*NOTE* first time using mermaid so there may be errors :)

```mermaid
classDiagram
    users "1"--"0..*" spot_likes
    users "1"--"0..*" spot_dislikes
    users "1"--"0..*" spots
    users "1"--"0..*" comments
    spot_likes "0..*"--"1" spots
    spot_dislikes "0..*"--"1" spots
    comments "0..*"--"1" spots
    class users{
        Long userId [PK]
        String username
        String passwordHash
    }
    class spots{
        Long id [PK]
        Long user.id [FK]
        String name
        String image
        String description
        Double lon
        Double lat
        Int likes
        Int dislikes
    }
    class comments{
        Long commentId [PK]
        String comment
        Long spot.id [FK]
        Long users.userId [FK]
    }
    class spot_likes{
        Long users.userId [PK]
        Long spots.id [PK]
    }
    class spot_dislikes{
        Long users.userId [PK]
        Long spots.id [PK]
    }
```
