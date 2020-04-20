import Flutter
import UIKit

public class SwiftDashcamPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "dashcam", binaryMessenger: registrar.messenger())
    let instance = SwiftDashcamPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    
    let arguments = call.arguments as? NSDictionary

    switch (call.method) {
    case "getPlatformVersion":
        result("iOS " + UIDevice.current.systemVersion)
        break;
    case "configureCamera":
        guard let w = arguments?["width"] as? Int else {return}
        guard let h = arguments?["height"] as? Int else {return}
        guard let rate = arguments?["frameRate"] as? Double else {return}
        let res = configureCamera(width: w, height: h, frameRate: rate)
        result("ConfigCamera call returns \(res)")
        break;
    default:
        result("\(call.method) is not implemented");
    }
  }
    
    private func configureCamera(width: Int, height: Int, frameRate: Double) -> Bool {
        return Dashcam().shouldSupportADAS
    }
}
