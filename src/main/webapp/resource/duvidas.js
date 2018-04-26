/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module("DuvidasApp", [])
        .value('urlBase', 'http://localhost:8080/jersey/rest/')
        .controller("DuvidaController", function ($http, urlBase) {
            var global = this;
            global.usuario = 'Gustavo Trigo';

            global.Duvidas = [];
            global.Duvida = undefined;

            global.novo = function () {
                global.duvida = {};
            };

            global.salvar = function () {
                var metodo = 'POST';
                if (global.duvida.id) {
                    metodo = 'PUT';
                }
                console.log(global.duvida);
                $http({
                    method: metodo,
                    url: urlBase + 'duvidas/',
                    data: global.duvida
                }).then(function successCallback(response) {
                    global.refreshList();
                }, function errorCallback(response) {
                    global.ocorreuErro();
                });
            };

            global.alterar = function (duvida) {
                global.duvida = duvida;
            };

            global.deletar = function (duvida) {
                global.duvida = duvida;

                $http({
                    method: 'DELETE',
                    url: urlBase + 'duvidas/' + global.duvida.id + '/'
                }).then(function successCallback(response) {
                    global.refreshList();
                }, function errorCallback(response) {
                    global.ocorreuErro();
                });
            };

            global.concluir = function (duvida) {
                global.duvida = duvida;

                $http({
                    method: 'PUT',
                    url: urlBase + 'duvidas/' + global.duvida.id + '/'
                }).then(function successCallback(response) {
                    global.refreshList();
                }, function errorCallback(response) {
                    global.ocorreuErro();
                });
            };

            global.ocorreuErro = function () {
                alert("Ocorreu um erro inesperado!");
            };

            global.refreshList = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'duvidas/'
                }).then(function successCallback(response) {
                    global.duvidas = response.data;
                    global.duvida = undefined;
                }, function errorCallback(response) {
                    global.ocorreuErro();
                });
            };

            global.activate = function () {
                global.refreshList();
            };
            global.activate();
        });