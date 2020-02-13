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