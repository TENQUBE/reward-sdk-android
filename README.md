# reward-sdk-android

## onPageLoaded() - Android

- 설명
    
    → 웹뷰가 정상적으로 로드 되었음을 확인하며 디바이스 Back Key의 권한을 얻습니다.
    이후 디바이스 Back Key가 선택되면 SDK에서 `window.onFinish()` 를 호출합니다.
    
- 요청
    
    ```tsx
    window.visualUI.onPageLoaded()
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.onPageLoaded(JSON.stringify({
      statusCode: 200
    }));
    ```
    

## finish()

- 설명
    
    → 현재의 웹뷰의 가장 상단에 있는 스택을 종료합니다.
    
- 요청
    
    ```tsx
    window.visualUI.finish()
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.finish(JSON.stringify({
      statusCode: 200
    }));
    ```
    

## setRefreshEnabled()  - Android

- 설명
    
    → 아래로 터치 무브 했을때 네이티브의 새로고침 기능을 활성화 할지의 여부를 설정합니다.
    
- 요청
    
    ```tsx
    window.visualUI.setRefreshEnabled(JSON.stringify({
      data: {
    	  enabled: true (or false)
      }
    })
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.setRefreshEnabled(JSON.stringify({
      statusCode: 200
    }));
    ```
    

## showToast()

- 설명
    
    → 토스트를 띄웁니다.
    
- 요청
    
    ```tsx
    // Android
    window.visualUI.showToast(JSON.stringify({
    	data: {
        message: "toast message"
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'showToast',
    	data: JSON.stringify({
    		message: 'toast message'
      })
    })
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.showToast(JSON.stringify({
      statusCode: 200
    }));
    ```
    

## showConfirm()

- 설명
    
    → 컨펌 박스를 띄웁니다.
    
- 요청
    
    ```tsx
    // Android
    window.visualUI.showConfirm(JSON.stringify({
      data: {
    	  title: "title",
    		message: "confirm message",
    	  positive: {
    	    button: {
    	   		text: "확인",
    	  		color: "#ffffff",
    		    bgColor: "#000000"
    	    }
    		},
    		negative: {
    		  button: {
    		   	text: "취소",
    				color: "#ffffff",
    			  bgColor: "#000000"
    		  }
    		}
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'showConfirm',
    	data: JSON.stringify({
    	  title: "title",
    		message: "confirm message",
    	  positive: {
    	    button: {
    	   		text: "확인",
    	  		color: "#ffffff",
    		    bgColor: "#000000"
    	    }
    		},
    		negative: {
    		  button: {
    		   	text: "취소",
    				color: "#ffffff",
    			  bgColor: "#000000"
    		  }
    		}
      })
    })
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.showConfirm(JSON.stringify({
      statusCode: 200,
      data: {
        confirm: true //(or false)
      }
    }));
    ```
    

## openSelectBox()

- 설명
    
    → 셀렉트 박스를 띄웁니다. (기존 바텀시트)
    
- 요청
    
    ```tsx
    // Android
    window.visualUI.openSelectBox(JSON.stringify({
      data: {
    	  title: "정렬기준 선택",
    	  selectedColor: "#111111",
    	  data: [{
    	    name: "인기순",
    	    orderByType: 1,
    	    isSelected: true
    	  }, {
    	    name: "최신순",
    	    orderByType: 2,
    	    isSelected: false
    	  }, {
    	    name: "이름순",
    	    orderByType: 3,
    	    isSelected: false
    	  }]
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'openSelectBox',
    	data: JSON.stringify({
    	    name: "인기순",
    	    orderByType: 1,
    	    isSelected: true
    	  }, {
    	    name: "최신순",
    	    orderByType: 2,
    	    isSelected: false
    	  }, {
    	    name: "이름순",
    	    orderByType: 3,
    	    isSelected: false
    	  }]
      })
    })
    ```
    
- 응답
    
    → SDK에서는 사용자가 선택한 data의 객체의 값을 가지고 콜백 함수를 호출해 줍니다.
    
    ```tsx
    // ex. 3번째 "이름순"을 선택하였을 때
    window.response.openSelectBox(JSON.stringify({
      statusCode: 200,
      data: {
        orderByType: 3
      }
    }));
    ```
    

## openNewView()

- 설명
    
    → 새로운 웹뷰를 띄웁니다. 
    
    → 새로운 웹뷰는 투명한 배경으로 Status bar 영역을 포함한 영역입니다.
    
    → ‘internal’ 일 경우 인앱 브라우저로, ‘external’ 일 경우 외부 브라우저(크롬, 사파리, 기타 내장 브라우저)를 호출합니다.
    
- 요청
    
    ```tsx
    // Android
    window.visualUI.openNewView(JSON.stringify({
      data: {
    	  type: "internal", // (or "external") 
    		url: "https://tenqube.com"
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'openNewView',
    	data: JSON.stringify({
    	  type: "internal", // (or "external") 
    		url: "https://tenqube.com"
      })
    })
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.openNewView(JSON.stringify({
      statusCode: 200
    }));
    ```
    

## showDatePicker()

- 설명
    
    → 데이트피커를 띄웁니다.
    
- 요청
    
    ```tsx
    // Android
    window.visualUI.showDatePicker(JSON.stringify({
      data: {
    	  date: '2022-02-01'
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'showDatePicker',
    	data: JSON.stringify({
    	  date: '2022-02-01'
      })
    })
    
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    window.response.showDatePicker(JSON.stringify({
      statusCode: 200,
    	data: {
        date: '2022-03-01'
      }
    }));
    ```
    

## changeTimePicker()

- 설명
    
    → 타임피커를 띄웁니다.
    
- 요청
    
    ```tsx
    window.visualUI.changeTimePicker(JSON.stringify({
    	data: {
    	  time: '14:30:00' //(시 분 초)
      }
    }));
    ```
    
- 응답
    
    → SDK에서는 요청을 정상적으로 받으면 아래의 응답을 호출합니다.
    
    ```tsx
    // Android
    window.response.changeTimePicker(JSON.stringify({
      statusCode: 200,
    	data: {
        time: '15:30:00'
      }
    }));
    
    // iOS
    window.webkit.messageHandlers.scriptHandler.postMessage({
    	fncName: 'showDatePicker',
    	data: JSON.stringify({
    	  time: '15:30:00'
      })
    })
    ```
