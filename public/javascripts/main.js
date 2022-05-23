
function random(min = 0, max = Number.MAX_SAFE_INTEGER) {
  return Math.floor(Math.random() * (max - min) + min);
} 

$(document).ready(function() {
  // screen.lockOrientation('portrait');

  // $('#board-size').change(function() {
  //   var opval = $(this).val();
  //   if (opval != '') {
  //     console.log("Sending ...");
  //     sendInitToServer();
  //     // $('#board-size').val("");
  //     // $("#play-pause-button").text("Pause"); 
  //     $("#play-pause-button").removeClass("play"); 
  //     $("#play-pause-button").addClass("pause"); 
  //     window.clearInterval(nextMoveIntervalEvent); 
  //     nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
  //   }
  // });

  $('#board-size').change(function() {
    // Trigger here your function:    
    console.log('Selected Option: ' + $(this).val() );
    var opval = $(this).val();
    if (opval != '') {
      console.log("Sending ...");
      sendInitToServer();
      // $('#board-size').val("");
      // $("#play-pause-button").text("Pause"); 
      $("#play-pause-button").removeClass("play"); 
      $("#play-pause-button").addClass("pause"); 
      window.clearInterval(nextMoveIntervalEvent); 
      nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
    }
  });

  // $('#board-size').click(function() {
  //   if ( $(this).data('clicks') == 1 ) {
  //     // Trigger here your function:    
  //     console.log('Selected Option: ' + $(this).val() );
  //     $(this).data('clicks', 0);
  //     var opval = $(this).val();
  //     if (opval != '') {
  //       console.log("Sending ...");
  //       sendInitToServer();
  //       $('#board-size').val("");
  //     }
  //   } else {
  //     console.log('first click');
  //     $(this).data('clicks', 1);
  //   }
  // });

  // $('#board-size').focusout( function() {
  //   $(this).data('clicks', 0);
  // });
});

function wait(ms){
var start = new Date().getTime();
var end = start;
while(end < start + ms) {
  end = new Date().getTime();
}
}

var webSocket;
var messageInput;

function init() {
var host = location.origin.replace(/^https/, 'wss').replace(/^http/, 'ws'); 
webSocket = new WebSocket(`${host}/ws`);
webSocket.onopen = onOpen;
webSocket.onclose = onClose;
webSocket.onmessage = onMessage;
webSocket.onerror = onError;
$("#message-input").focus();
}

$("#dark-mode").change(function() {
if (this.checked) {
  $("body").addClass("dark-mode");
  $("body").removeClass("light-mode");
} else {
  $("body").addClass("light-mode");
  $("body").removeClass("dark-mode");
}
})

function onOpen(event) {
  consoleLog("CONNECTED to server");
}

function onClose(event) {
  consoleLog("DISCONNECTED from server");
  consoleLog("Re-initializing a new fresh connection so server will be available when the next new game's requested");
  init();
}

function onError(event) {
  consoleLog("ERROR: " + event.data);
  consoleLog("ERROR: " + JSON.stringify(event));
}

function onMessage(event) {
  console.log(event.data);
  let receivedData = JSON.parse(event.data);
  console.log("New Data: ", receivedData);
  // get the text from the "body" field of the json we
  // receive from the server.
  if (receivedData.body.startsWith("Invalid")) {
    consoleLog("ERROR: " + receivedData.body); 
    alert(receivedData.body);
  } else {
    $('#remaining-moves').text(receivedData.body);
    nextMove();
  }
}

function consoleLog(message) {
  console.log("New message: ", message);
}

window.addEventListener("load", init, false);

$("#play-pause-button").click(function (e) {
if ($("#play-pause-button").attr("class").indexOf("play") == -1 && !$("#play-pause-button").attr("class").indexOf("pause") == -1) {
  $("#play-pause-button").removeClass("pause");
  $("#play-pause-button").addClass("play");
} else if ($("#play-pause-button").attr("class").indexOf("play") != -1) {
  nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
  $("#play-pause-button").removeClass("play");
  $("#play-pause-button").addClass("pause");
} else if ($("#play-pause-button").attr("class").indexOf("pause") != -1) {
  window.clearInterval(nextMoveIntervalEvent); 
  $("#play-pause-button").removeClass("pause");
  $("#play-pause-button").addClass("play");
} else {
  $("#play-pause-button").removeClass("play");
  $("#play-pause-button").removeClass("pause");
} 
});

$("#board").click(function (e) {
  nextMove();
});

var interval = 220;
var nextMoveIntervalEvent = window.setInterval(nextMove, interval);

// continue to step while user presses the any key
$(window).on("keydown", function (e) {
  if (e.which == 13 || e.which == 32 || e.which == 9) { // 13 is <enter>, 32 is <space>, 9 is <tab>
      nextMove();
      return false;
  }
});

$("#remaining-moves").on("ontouchstart", function(e) {
nextMove();
return false; 
});
$("#remaining-moves").on("ontouchmove", function(e) {
nextMove();
return false; 
});
$("#step-button").on("ontouchstart", function(e) {
nextMove();
return false; 
});
$("#step-button").on("ontouchmove", function(e) {
nextMove();
return false; 
});

function sendInitToServer() {
  messageInput = $("#board-size").val();
  // if the trimmed message was blank, return now
  if ($.trim(messageInput) == "") {
      return false;
  }
  // create the message as json
  let jsonMessage = {
      message: messageInput
  };
  // send our json message to the server
  sendToServer(jsonMessage);
}

function sendStepToServer() {
  messageInput = $("#board-size").val();
  // if the trimmed message was blank, return now
  if ($.trim(messageInput) == "") {
      return false;
  }
  // create the message as json
  let jsonMessage = {
      message: messageInput
  };
  // send our json message to the server
  sendToServer(jsonMessage);
}

// send the data to the server using the WebSocket
function sendToServer(jsonMessage) {
  if(webSocket.readyState == WebSocket.OPEN) {
      consoleLog("SENT: " + jsonMessage.message);
      webSocket.send(JSON.stringify(jsonMessage));
  } else {
      consoleLog("Could not send data. Websocket is not open.");
  }
}

// Convert a given string such as: ((1,2,3),(4,5,6),(7,8,*))
// into markup as an unordered list
function boardMarkup(b, gridSize) {
  var board = b.replace(' ', '');
  var markup = '<ul><li>';
  for (var i = 0; i < board.length; i++) {
    if (i % gridSize == 0) {
      markup += '</li><li>';
    }
    var curr = board.charAt(i);
    var next = ''; // next is to look ahead for * so we know to use special css class on its li element
    if ((i + 1) < board.length) {
      next = board.charAt(i + 1)
    }
    if (next == '*') {
      if (curr == '(') {
        markup += "<ul><li class='empty'>";
      } else if (curr == ')') {
        markup += "</li></ul>";
      } else if (curr == ',') {
        markup += "</li><li class='empty'>";
      } else {
        if (curr == "*") {
          markup += " ";
        } else {
          markup += curr
        }
      }
    } else {
      if (curr == '(') {
        markup += "<ul><li>";
      } else if (curr == ')') {
        markup += "</li></ul>";
      } else if (curr == ',') {
        markup += "</li><li>";
      } else {
        if (curr == "*") {
          markup += " ";
        } else {
          markup += curr
        }
      }
    }
  }
  markup += '</li></ul>';
  return markup;
}

function head(lst) {
  return lst[0];
}

function tail(lst) {
  return lst.slice(1);
}

function concatenate(lst, delimiter) {
  var result = ''
  for (i = 0; i < lst.length; i++) {
    if (result.length > 0) {
      result += delimiter
    }
    result += lst[i];
  }
  return result;
}

function nextMove() {
  var moves = $('#remaining-moves').text().split('|')
  var curr = head(moves);
  var remaining = tail(moves);
  var remainingCount = remaining.length;
  // if ($('#remaining-moves').text() != 'remaining: 0' && $('#remaining-moves').text() != '') {
  //   $('#board').html(boardMarkup(curr));
  //   $('#remaining-moves').text(concatenate(remaining, '|'));
  //   $('#remaining-count').text('remaining: ' + remainingCount)
  //   if (remainingCount == 0) {
  //     // $("#play-pause-button").text("");
  //     $("#play-pause-button").removeClass("play");
  //     $("#play-pause-button").removeClass("pause");
  //     window.clearInterval(nextMoveIntervalEvent); 
  //     nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
  //   }
  // }
}

