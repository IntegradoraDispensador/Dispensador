<template>
    <div class="container">
      
            <form class="form-inline" style="margin-top: 200px">
        <div class="form-group mb-2">
            <input type="text" readonly class="form-control-plaintext"  value="Ingresa el token de Adafruit">
        </div>
        <div class="form-group mx-sm-3 mb-2">
           
            <input v-model="token" type="text" class="form-control">
        </div>
        <button type="button" v-on:click="insertToken()" class="btn btn-primary mb-2">Confirm identity</button>
        </form>
                
    </div>
</template>

<script>
export default {

     data() {

     return { 

         token : ""
        
     }
    },
    

    methods: { 

       
        insertToken(){
            let self = this;
                axios({
                    method: 'post',
                    url: '/tokens',
                    data: JSON.stringify({
                        token: self.token
                       
                    })
                })
                .then(response => {

                    console.log(response)
                })
                .catch(errors => {
                    console.log("error: " + errors)
                });
             
                    

    }
    }
}
</script>