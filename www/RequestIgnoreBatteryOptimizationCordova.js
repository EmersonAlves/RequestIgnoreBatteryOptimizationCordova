module.exports.requestPermission = function(successCallback, errorCallback) {
  cordova.exec(
    () => {
      successCallback();
    },
    () => {
      errorCallback();
    },
    "RequestIgnoreBatteryOptimizationCordova",
    "requestPermission",
    []
  );
};

module.exports.checkPermission = function(callback) {
  cordova.exec(
    callback,
    () => {},
    "RequestIgnoreBatteryOptimizationCordova",
    "checkPermission",
    []
  );
};