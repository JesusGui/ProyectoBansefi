var storage = (function () {
	
	var dialogTime = 10000;
	var timeoutTime = 60000;
	var dialogTimer = null;
	var timeoutTimer = null;
	
	function updateDialogTime(contextTimeOut){
		dialogTime = (contextTimeOut*60000)-timeoutTime*8;
	}
	
	function askToSave(){
		timeoutTimer = setTimeout(function(){
			cerrarSesionTimeout();}, timeoutTime * 3);
		PF('dlgStore').show();
	}
	
	function popUpOk() {
		deleteTimer(timeoutTimer);
	  }
	
	function popUpCancel() {
		deleteTimer(timeoutTimer);
	    goHome();
	  }
	
	function cerrarSesion(){		
		document.getElementById("formularioPlantilla:cerrarSesion").click();	
	}
	
	function cerrarSesionTimeout(){		
		document.getElementById("formularioPlantilla:cerrarSesionTimeout").click();	
	}
	
	function goHome(){		
		var formId = $("#storage").closest("form").attr("id");
		document.getElementById(formId+":goHome").click();	
	}
	
	function add(id, data) {
		if (id === null || id === "" ||
			data === null || data === "")
			return;
		localStorage.setItem(id, data);
	}
	
	function recoverAndRemove(id) {
		if (id === null || id === "")
			return;
		var item = localStorage.getItem(id);
		remove(id);
		return item;
	}
	
	function remove(id) {
		localStorage.removeItem(id);
	}
	
	function listData(){
		var list = [];
		if(localStorage.length > 0){
			for (var key in localStorage) {
				if(hasDataExpired(key)){
					remove(key);
				}else{		
				    list.push(key.substring(8));
				}
			}
		}
		return list;
	}
	
	function listDataSize(){
		return listData().length;
	}
	
	function stringListData(){
		var stringList = "";
		if(localStorage.length > 0){
			for (var key in localStorage) {				
				stringList+=key+"|";
			}
			stringList = stringList.slice(0,-1);
		}
		return stringList;
	}
	
	function deleteTimer(timerReference){
		if (timerReference) {
		    clearTimeout(timerReference); //cancel the previous timer.
		    timerReference = null;
		}
	}
	
	function hasDataExpired(key){
		var todayDate = $.datepicker.formatDate('dd-mm-yy', new Date());
		var arr = key.split('+');
		return arr[3] !== todayDate;
	}
	
	return {
		dlgStoreOK: function () {
			popUpOk();
		},
		dlgStoreCancel: function () {
			popUpCancel();
		},
		saveData: function (id, data) {
			add(id, data);
			goHome();
		},
		recoverData: function (id) {
			return recoverAndRemove(id);
		},
		removeData: function (id) {
			return remove(id);
		},
		listSavedData: function () {
			return stringListData();
		},
		getListDataSize: function(){
			return listDataSize();
		},
		startTimeOut: function () {
			dialogTimer = setTimeout(function(){
				askToSave();}, dialogTime);
		},
		restartTimeOut: function () {
			deleteTimeOut();
			startTimeOut();
		},
		deleteTimeOut: function () {
			deleteTimer(timeoutTimer);
			deleteTimer(dialogTimer);			
		},
		setDialogTime: function (contextTimeOut){
			updateDialogTime(contextTimeOut);
		}
	};
	
})();