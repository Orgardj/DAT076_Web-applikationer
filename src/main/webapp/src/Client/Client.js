/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

export default class Client{
        sayhello() {
            let result = new Promise((resolve, reject) => {
                let request = new XMLHttpRequest();
                request.open("GET","http://localhost:8080/lab3/resources/ws/sayhello");
                request.onreadystatechange = () => {
                    let raw = request.responseText;
                    let objectified = JSON.parse(raw);
                    resolve(objectified);
                }
                request.send();
            });
            return result;
        }
}
