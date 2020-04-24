<?php

namespace App;
use Illuminate\Http\Request;


use Illuminate\Notifications\Notifiable;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Laravel\Passport\HasApiTokens;


class User extends Authenticatable
{
    use HasApiTokens, Notifiable;


    
   

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    /**
     * The attributes that should be cast to native types.
     *
     * @var array
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function setAdafruitToken($token)
    {
        $this->token = $token;
        if($this->save())
        {
            return response()->json(['message'=>'Exito','token'=>$this->getAdafruitToken()],200);
        }
        return response()->json(['message'=>'Error, intentelo de nuevo'],401);
    }

    public function getAdafruitToken()
    {
        return $this->token;
    }

    
}
